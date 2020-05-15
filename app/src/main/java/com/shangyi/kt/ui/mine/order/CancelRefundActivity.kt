package com.shangyi.kt.ui.mine.order

import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityCancelRefundBinding
import com.shangyi.business.weight.dialog.IosAlertDialog
import com.shangyi.kt.ui.mine.bean.RefundOrderBean
import com.shangyi.kt.ui.mine.order.adapter.RefundGoodsAdapter
import com.shangyi.kt.ui.mine.order.model.CancelRefundModel
import com.shangyi.kt.ui.order.bean.OrderDetailGood
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
            if (it) {
                finish()
            }
        })
        mBinding.vm?.refundData?.observe(this, Observer {
            if (it != null) {
                bandData(it)
            }
        })
    }

    private var orderNum = ""  // 订单编号
    private var orderRid = ""  // 退款商品ID
    private var orderData: RefundOrderBean? = null  // 商品信息
    private val adapter = RefundGoodsAdapter()

    override fun initView() {
        orderNum = intent.getStringExtra("orderNum")
        orderRid = intent.getStringExtra("orderRid")

        fqtkLayout.visibility = View.VISIBLE
        refundSuccessLayout.visibility = View.GONE
        tvCloseRefund.visibility = View.GONE


        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tvCancelRefund -> {
                // 取消退款
                ycshDialog.show()
            }
            R.id.tvChangeReport -> {
                // 修改申请
                if (orderData?.goods.isNullOrEmpty()) {
                    return
                }
                val intent = Intent(this, RefundActivity::class.java)
                val data = OrderDetailGood(
                        orderData?.goods!![0].gid,
                        orderData?.goods!![0].good_name,
                        orderData?.goods!![0].number,
                        orderData?.goods!![0].oid,
                        orderData?.goods!![0].pic,
                        orderData?.goods!![0].price,
                        orderData?.goods!![0].sku_info
                )
                intent.putExtra("order_num", orderNum)
                intent.putExtra("goodsInfo", data)
                intent.putExtra("orderRid", orderRid)
                startActivityForResult(intent, 1)
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

    override fun onResume() {
        super.onResume()
        mBinding.vm?.loadRefundData(orderRid)
    }

    /**
     * 绑定数据
     */
    private fun bandData(it: RefundOrderBean) {
        orderData = it
        //状态（0-申请退款，1-已退款，2-退款失败，3、拒绝退款）
        fqtkLayout.visibility = View.GONE
        refundSuccessLayout.visibility = View.GONE
        tvCloseRefund.visibility = View.GONE

        when (it.status) {
            0 -> {
                tvStatusStr.text = "请等待商家处理"
                fqtkLayout.visibility = View.VISIBLE
            }
            1 -> {
                tvStatusStr.text = "退款成功"
                refundSuccessLayout.visibility = View.VISIBLE
            }
            3 -> {
                tvStatusStr.text = "退款关闭"
                tvCloseRefund.visibility = View.VISIBLE
            }
        }
        tvTx1.text = "退款原因：${it.remark}"
        tvTx2.text = "退款金额：${it.refund_price}"
        tvTx3.text = "申请时间：${it.create_time}"
        tvTx4.text = "退款编号：${it.refund_sn}"

        adapter.setList(it.goods)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) return
        if (requestCode == 1) {
            val isFinish = data.getBooleanExtra("isFinish", false)
            if (isFinish){
                finish()
            }
        }
    }

}
