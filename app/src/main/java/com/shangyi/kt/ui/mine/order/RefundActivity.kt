package com.shangyi.kt.ui.mine.order

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.sdxxtop.base.BaseKTActivity
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityRefundBinding
import com.shangyi.business.utils.qiniu.QnUploadUtil
import com.shangyi.business.weight.dialog.RefundOrderDialog
import com.shangyi.kt.ui.mine.bean.RefundImgParams
import com.shangyi.kt.ui.mine.order.model.RefundModel
import com.shangyi.kt.ui.mine.weight.imgselect.CusMediaSelect
import com.shangyi.kt.ui.order.bean.OrderDetailGood
import com.tencent.mm.opensdk.utils.Log
import kotlinx.android.synthetic.main.activity_refund.*

class RefundActivity : BaseKTActivity<ActivityRefundBinding, RefundModel>() {

    override fun layoutId() = R.layout.activity_refund
    override fun vmClazz() = RefundModel::class.java

    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    override fun initObserve() {
        mBinding.vm?.refundSuccess?.observe(this, Observer {
            if (it) {
                if (orderRid.isNullOrEmpty()) {
                    UIUtils.showToast("申请退款成功")
                } else {
                    UIUtils.showToast("修改成功")
                }
                val intent = Intent()
                intent.putExtra("isFinish", true)
                setResult(2, intent)
                finish()
            }
        })
    }

    private var goodsInfo: OrderDetailGood? = null   // 传进来的商品信息
    private var orderNum = ""       // 传进来的商品信息
    private var orderRid = ""       // 退款ID
    private var imgUrlList = ArrayList<RefundImgParams>()  // 需要上传的图片链接

    /**
     * 退款原因的选择框
     */
    private val reasonDialog: RefundOrderDialog by lazy {
        val cancelDialog = RefundOrderDialog.newInstance()
        cancelDialog.setOnDismiss(object : RefundOrderDialog.OnDissListener {
            override fun onDismiss() {
            }

            override fun onItemClick(str: String?) {
                tvSelectReason.text = str
            }
        })
        cancelDialog
    }

    override fun initView() {
        goodsInfo = intent.getParcelableExtra("goodsInfo")
        orderRid = intent.getStringExtra("orderRid") ?: ""
        orderNum = intent.getStringExtra("order_num") ?: ""
        glideImageView.loadImage(goodsInfo?.pic ?: "")
        tvName.text = goodsInfo?.good_name
        tvAttr.text = goodsInfo?.sku_info
        tvAllPrice.text = "¥${goodsInfo?.price}"
        tvTemp.text = "不可修改，最多¥${goodsInfo?.price}。"
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tvSubmit -> {
                // 提交
                submit()
            }

            R.id.selectLayout -> {
                // 退款原因
                reasonDialog.show(supportFragmentManager, "")
            }
        }
    }

    /**
     * 提交
     */
    private fun submit() {
        if (tvSelectReason.text.isNullOrEmpty()) {
            UIUtils.showToast("请选择退款原因")
            return
        }
        if (!cusMeditLayout.getImagePushPath().isNullOrEmpty()) {
            imgUrlList.clear()
            mLoadingDialog.show()
            QnUploadUtil.getInstance().upLoad(cusMeditLayout.getImagePushPath())
            QnUploadUtil.getInstance().setOnImgUpLoadListener { _it ->
                mLoadingDialog.dismiss()
                _it.values.forEach {
                    if (it != null) {
                        imgUrlList.add(it as RefundImgParams)
                    }
                }
                mBinding.vm?.refundOrder(
                        orderNum,
                        goodsInfo?.gid ?: 0,
                        tvSelectReason.text.toString(),
                        editText.text.toString().trim(),
                        orderRid,
                        imgUrlList
                )
            }
        } else {
            mBinding.vm?.refundOrder(
                    orderNum,
                    goodsInfo?.gid ?: 0,
                    tvSelectReason.text.toString(),
                    editText.text.toString().trim(),
                    orderRid,
                    imgUrlList
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cusMeditLayout?.callActivityResult(requestCode, resultCode, data)
    }


}
