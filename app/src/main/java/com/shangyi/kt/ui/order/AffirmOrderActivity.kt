package com.shangyi.kt.ui.order

import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alipay.sdk.app.PayTask
import com.sdxxtop.base.BaseKTActivity
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityAffirmOrderBinding
import com.shangyi.business.utils.CheckUtil
import com.shangyi.business.weight.CustomOrderTextView
import com.shangyi.business.weight.dialog.IosAlertDialog
import com.shangyi.business.weight.dialog.YhqDialog
import com.shangyi.kt.fragment.car.entity.AddressInfoBean
import com.shangyi.kt.fragment.car.entity.CommitOrderBean
import com.shangyi.kt.ui.address.AddressListActivity
import com.shangyi.kt.ui.address.bean.AreaListBean
import com.shangyi.kt.ui.order.adapter.OrderGoodsAdapter
import com.shangyi.kt.ui.order.bean.CommitOrderYhqData
import com.shangyi.kt.ui.order.bean.OrderListJsonBean
import com.shangyi.kt.ui.order.bean.WxRequest
import com.shangyi.kt.ui.order.model.CommitOrderModel
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import kotlinx.android.synthetic.main.activity_affirm_order.*
import kotlinx.android.synthetic.main.dialog_pay_type.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.ref.WeakReference
import java.math.RoundingMode
import java.text.DecimalFormat


class AffirmOrderActivity : BaseKTActivity<ActivityAffirmOrderBinding, CommitOrderModel>(), PayBottomDialog.OnBottomItemClickListener {
    override fun vmClazz() = CommitOrderModel::class.java
    override fun layoutId() = R.layout.activity_affirm_order

    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    override fun initObserve() {
        mBinding.vm?.areaBean?.observe(this, Observer {
            if (it != null) {
                tvAdsStr.text = "${it.provice?.name}${it.city?.name}${it.county?.name}${it.detail}"
                addressId = it.id
                mBinding.vm?.loadYunfei(list, addressId)
            }
        })
        mBinding.vm?.querenOrders?.observe(this, Observer {
            orderNumber = it.order_num
            orderId = it.order_id
            payBottomDialog.bottmShow()
        })

        mBinding.vm?.orderInfo?.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                startZfb(it)
            }
        })
        mBinding.vm?.wxPayInfo?.observe(this, Observer {
            if (it != null) {
                weChatPay(it)
            }
        })

        /**
         * 支付包支付成功，跳转
         */
        mBinding.vm?.aliPaySuccess?.observe(this, Observer {
            if (it) {
                payBottomDialog.dismiss()
                UIUtils.showToast("支付成功")
                val intent = Intent(this@AffirmOrderActivity, PaySuccessActivity::class.java)
                intent.putExtra("orderId", orderId)
                intent.putExtra("totalPrice", yhPrice)
                startActivity(intent)
                finish()
            }
        })
    }

    private var orderData: ArrayList<CommitOrderBean>? = null  // 商品的数据
    private var addressData: AddressInfoBean? = null  // 地址信息
    private var addressId = 0   // 地址ID
    private var list = ArrayList<OrderListJsonBean>()  // 请求接口的商品列表
    private val mHandler = MyHandler(this)
    private var orderNumber = ""  // 订单编号
    private var orderId = ""  // 订单Id
    private var payType = 1 // 1 -- 支付宝  2 -- 微信  默认选中支付宝
    private var totalPrice = 0f // 总金额
    private var yhPrice = 0f // 使用优惠券的价格
    private var fanPrice = 0f // 返现金额
    private var yhqData = ArrayList<Int>()  // 优惠券ID集合

    /**
     * 优惠券对话框
     */
    private val yhqDialog: YhqDialog by lazy {
        val dialog = YhqDialog.newInstance(list)
        dialog.setOnYhqSelectListener {
            refreshPrice(it)
        }
        dialog
    }

    /**
     * 微信支付api
     */
    private val api: IWXAPI by lazy {
        var api = WXAPIFactory.createWXAPI(this@AffirmOrderActivity, "wx8c512b137c836be1")
        api.registerApp("wx8c512b137c836be1")
        api
    }

    /**
     * 取消的弹框
     */
    private val cancelPay: IosAlertDialog by lazy {
        val dialog = IosAlertDialog(this)
                .builder()
                .setTitle("确认放弃付款吗？")
                .setMsg(" ")
                .setMsg2("超过支付时效后，订单会被取消哦")
                .setMsg3(" ")
                .setPositiveButton("继续支付", Color.parseColor("#FF2942")) {}
                .setNegativeButton("确定离开", Color.parseColor("#333333")) {
                    finish()
                }
        dialog
    }

    /**
     * 支付类型的选择框
     */
    private val payBottomDialog: PayBottomDialog by lazy {
        val mDialogView = layoutInflater.inflate(R.layout.dialog_pay_type, null)
        mDialogView.iv_buy_alipay_select.isEnabled = true
        mDialogView.iv_buy_weichat_select.isEnabled = false
        mDialogView.tv_num.text = "¥${yhPrice}"
        val mDialog = PayBottomDialog(this@AffirmOrderActivity, mDialogView, intArrayOf(R.id.tv_confirm, R.id.img_cancel))
        mDialogView.ll_pay_weichat.setOnClickListener {
            //wechat
            payType = 2
            mDialogView.iv_buy_alipay_select.isEnabled = false
            mDialogView.iv_buy_weichat_select.isEnabled = true
        }
        mDialogView.ll_pay_ali.setOnClickListener {
            //ali
            payType = 1
            mDialogView.iv_buy_alipay_select.isEnabled = true
            mDialogView.iv_buy_weichat_select.isEnabled = false
        }
        mDialog.setOnBottomItemClickListener(this)
        mDialog
    }

    override fun initView() {
        titleView.resetBackListener(this)
        orderData = intent.getSerializableExtra("orderData") as ArrayList<CommitOrderBean>
        addressData = intent.getParcelableExtra<AddressInfoBean>("addressData")
        addressId = addressData?.addressId ?: 0
        tvName.text = addressData?.name
        tvPhone.text = addressData?.phone
        tvAdsStr.text = addressData?.addressDesc

//        Log.e("orderData -- ", "${orderData.toString()}")

        if (orderData == null) {
            return
        }
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = OrderGoodsAdapter(orderData)
        setCommitData()

        tvPrice.text = "¥$totalPrice"
        yhPrice = totalPrice
        tvTotalPrice.text = "$yhPrice"
        tvFanTx.text = "下单返：¥${fanPrice}"
    }

    override fun initData() {
        if (addressData == null) {
            mBinding.vm?.loadAddress()
        } else {
            mBinding.vm?.loadYunfei(list, addressId)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.adsLayout -> {
                // 选择地址
                startActivityForResult(Intent(this@AffirmOrderActivity, AddressListActivity::class.java), 11)
            }
            R.id.tvCommotOrder -> {
                // 提交订单
                commitOrder()
            }
            R.id.ll_back -> {
                // 放弃支付
                cancelPay.show()
            }
            R.id.yhqLayout -> {
                yhqDialog.show(supportFragmentManager, "")
            }
        }
    }

    /**
     * 提交订单
     */
    private fun commitOrder() {
        setCommitData()
        mBinding.vm?.commitOrder(list, addressId, yhqData)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) return@onActivityResult
        if (requestCode == 11) {
            val item = data.getParcelableExtra<AreaListBean?>("areaBean")
            mBinding.vm?.areaBean?.value = item
        }
    }

    /**
     * 调用支付宝支付接口
     */
    private fun startZfb(from: String) {
        Thread(Runnable { //调用支付宝
            val payTask = PayTask(this)
            val result = payTask.pay(from, true)
            val msg = Message()
            msg.what = 0
            msg.obj = result
            mHandler.sendMessage(msg)
        }).start()
    }

    /**
     * 处理支付宝支付的Handler
     */
    inner class MyHandler(mActivity: AffirmOrderActivity) : Handler() {
        private val mActivity: WeakReference<AffirmOrderActivity> = WeakReference(mActivity)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val activity = mActivity.get()
            if (activity != null) {
                when (msg.what) {
                    0 -> {
                        val result = (msg.obj as String).replace("{", "")
                                .replace("}", "").replace("resultStatus=", "")
                                .replace("memo=", "").replace("result=", "")
                        Log.d("MainActivity:", result)
                        val num = result.split(";").toTypedArray()[0]
                        showAliPayInfo(num)
                    }
                }
            }
        }
    }

    /**
     * 支付宝支付订单查询
     */
    fun showAliPayInfo(num: String) {
        var result = when (num) {
            "9000" -> {
                mBinding.vm?.notifyOrder(orderNumber)
                ""
            }
            "8000" -> "支付结果未知，请联系客服"
            "4000" -> "订单支付失败"
            "5000" -> "重复请求"
            "6001" -> "订单取消成功"
            "6002" -> "网络连接出错"
            "6004" -> "支付结果未知，请联系客服"
            else -> "支付失败，请联系客服"
        }
        if (!result.isNullOrEmpty()) {
            UIUtils.showToast(result)
        }
    }

    override fun onBottomItemClick(payBottomDialog: PayBottomDialog?, view: View?) {
        when (view?.id) {
            R.id.tv_confirm -> {
                if (payType == 1) {
                    // aliPay
                    mBinding.vm?.getPayInfo(orderId, payType)
                } else {
                    //wechatPay
                    if (CheckUtil.isWeixinAvilible(this)) {
                        mBinding.vm?.getWxPayInfo(orderId, payType)
                    } else {
                        Toast.makeText(this, "检测到未安装微信支付取消。。。", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            R.id.img_cancel -> {
                payBottomDialog?.dismiss()
            }
        }
    }

    /**
     * 微信支付
     */
    private fun weChatPay(it: WxRequest) {
        val req = PayReq()
        req.appId = it.appid
        req.partnerId = it.partnerid
        req.prepayId = it.prepayid
        req.nonceStr = it.noncestr
        req.timeStamp = "${it.timestamp}"
        req.packageValue = it.`package`
        req.sign = it.sign
        req.extData = "app data" // optional
        api.sendReq(req)
    }

    /**
     * 处理提交订单提交的数据
     */
    private fun setCommitData() {
        list.clear()
        totalPrice = 0f
        for (item in orderData!!) {
            item.goodsInfos?.forEach {
                val goodsItem = OrderListJsonBean(it.goodsId, it.goodsSpecId, it.goodsCount, it.goodsImg
                        ?: "")
                list.add(goodsItem)
            }
            list[list.size - 1].remark = item.psText ?: ""
            // 总金额相加
            totalPrice += item.totalPrice
            fanPrice += item.fanPrice
        }
    }

    /**************** 微信支付成功 ****************/
    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: String?) {
        if (event.equals("0")) {
            payBottomDialog.dismiss()
            UIUtils.showToast("支付成功")
            val intent = Intent(this@AffirmOrderActivity, PaySuccessActivity::class.java)
            intent.putExtra("orderId", orderId)
            intent.putExtra("totalPrice", yhPrice)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
    /**************** 微信支付成功 ****************/


    /**
     * 选择优惠券的回掉
     * 进行价格的调整
     */
    private fun refreshPrice(it: List<CommitOrderYhqData>) {
        selectYhqLayout.removeAllViews()
        yhLayout.removeAllViews()
        yhqData.clear()

        it.forEach {
            var textView1 = CustomOrderTextView(this@AffirmOrderActivity)
            textView1.setData(getYouhuiquanStr(it), " ")
            selectYhqLayout.addView(textView1)

            var textView = CustomOrderTextView(this@AffirmOrderActivity)
            textView.setData(getYouhuiquanStr(it), "- ${it.price}")
            yhLayout.addView(textView)

            yhqData.add(it.id)
        }
        var textView = CustomOrderTextView(this@AffirmOrderActivity)
        textView.setData(" ", "合计   " + getYhPrice(it))
        yhLayout.addView(textView)

        tvTotalPrice.text = "${getYhPrice(it)}"
    }

    /**
     * 获取优惠完的金额
     */
    private fun getYhPrice(it: List<CommitOrderYhqData>): String {
        yhPrice = totalPrice
        it.forEach {
            yhPrice -= it.price
        }
        val df = DecimalFormat("0.00")
        df.roundingMode = RoundingMode.HALF_UP
        yhPrice = df.format(yhPrice).toFloat()
        return df.format(yhPrice)
    }

    /**
     * 获取代金券的描述信息
     */
    fun getYouhuiquanStr(bean: CommitOrderYhqData): String {
        return when (bean.type) {
            1 -> {  // 满减
                "${"领券满" + bean.full_price + "减" + bean.price}"
            }
            2 -> {   // 代金券
                "${"领券立减" + bean.price}"
            }
            3 -> {
                "兑换券"
            }
            else -> ""
        }
    }
}
