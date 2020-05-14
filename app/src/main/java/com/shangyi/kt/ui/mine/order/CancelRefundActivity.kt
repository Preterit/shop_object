package com.shangyi.kt.ui.mine.order

import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.lifecycle.Observer
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityCancelRefundBinding
import com.shangyi.business.weight.dialog.IosAlertDialog
import com.shangyi.kt.ui.mine.order.model.CancelRefundModel
import kotlinx.android.synthetic.main.activity_cancel_refund.*

class CancelRefundActivity : BaseKTActivity<ActivityCancelRefundBinding, CancelRefundModel>() {

    override fun layoutId() = R.layout.activity_cancel_refund
    override fun vmClazz() = CancelRefundModel::class.java

    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    override fun initObserve() {
        mBinding.vm?.cancelRefundSuccess?.observe(this, Observer {
            if (it){
                finish()
            }
        })
    }

    private var orderNum = ""

    override fun initView() {
        orderNum = intent.getStringExtra("orderNum")
        fqtkLayout.visibility = View.VISIBLE
        refundSuccessLayout.visibility = View.GONE
        tvCloseRefund.visibility = View.GONE
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tvCancelRefund -> {
                // 取消退款
                ycshDialog.show()
            }
            R.id.tvChangeReport -> {
                // 修改申请
                val intent = Intent(this, RefundActivity::class.java)
                intent.putExtra("order_num", orderNum)
                startActivity(intent)
            }
        }
    }

    /**
     * 延迟收货
     */
    private val ycshDialog: IosAlertDialog by lazy {
        val dialog = IosAlertDialog(this)
                .builder()
                .setTitle("取消退款")
                .setHeightMsg("请确认是否要申请取消退款？")
                .setPositiveButton("确认", Color.parseColor("#FF2942")) {
                    mBinding.vm?.cancelRefund(orderNum)
                }
                .setNegativeButton("取消", Color.parseColor("#333333")) {

                }
        dialog
    }
}
