package com.shangyi.kt.ui.mine.order

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.sdxxtop.base.BaseKTActivity
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityRefundBinding
import com.shangyi.business.weight.dialog.CancelOrderDialog
import com.shangyi.business.weight.dialog.RefundOrderDialog
import com.shangyi.kt.ui.mine.order.model.RefundModel
import com.shangyi.kt.ui.order.bean.OrderDetailGood
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
                intent.putExtra("isFinish",true)
                setResult(2,intent)
                finish()
            }
        })
    }

    private var goodsInfo: OrderDetailGood? = null   // 传进来的商品信息
    private var orderNum = ""       // 传进来的商品信息
    private var orderRid = ""       // 退款ID

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
        orderRid = intent.getStringExtra("orderRid")?:""
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
                if (tvSelectReason.text.isNullOrEmpty()) {
                    UIUtils.showToast("请选择退款原因")
                    return
                }
                mBinding.vm?.refundOrder(
                        orderNum,
                        goodsInfo?.gid ?: 0,
                        tvSelectReason.text.toString(),
                        editText.text.toString().trim(),
                        orderRid
                )
            }

            R.id.selectLayout -> {
                // 退款原因
                reasonDialog.show(supportFragmentManager, "")
            }
        }
    }
}
