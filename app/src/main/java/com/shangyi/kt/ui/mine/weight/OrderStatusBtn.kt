package com.shangyi.kt.ui.mine.weight

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.weight.dialog.CancelOrderDialog
import com.shangyi.business.weight.dialog.IosAlertDialog
import com.shangyi.kt.ui.mine.bean.OrderDetailAddress
import com.shangyi.kt.ui.mine.bean.PayDialogData
import com.shangyi.kt.ui.mine.order.CancelRefundActivity
import com.shangyi.kt.ui.mine.order.ChangeAddressActivity
import com.shangyi.kt.ui.mine.order.OrderDetailActivity
import com.shangyi.kt.ui.order.bean.OrderDetailInfoBean
import com.shangyi.kt.ui.pingjia.AddPinglunActivity
import kotlinx.android.synthetic.main.item_order_detail_status_btn.view.*

/**
 * Date:2020/5/11
 * author:lwb
 * Desc:
 */
class OrderStatusBtn constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_order_detail_status_btn, this)
    }

    /**
     * 设置btn内容
     */
    fun setData(title: String) {
        text.text = title
        when (title) {
            "评价", "确认收货", "付款" -> {
                text.setTextColor(resources.getColor(R.color.red))
                text.setBackgroundResource(R.drawable.shape_pay_btn_bg)
            }
            else -> {
                text.setTextColor(resources.getColor(R.color.color_999999))
                text.setBackgroundResource(R.drawable.shape_order_list_item_btn_gray_bg)
            }
        }

        text.setOnClickListener {
            onItemClick(title)
        }
    }

    var orderActivity: OrderDetailActivity? = null
    var orderNum: String? = null // 订单编号
    var orderData: OrderDetailInfoBean? = null // 订单数据

    /**
     * 设置按钮点击事件
     */
    fun onItemClick(title: String) {
        if (context is OrderDetailActivity) {
            orderActivity = context as OrderDetailActivity
            orderData = orderActivity?.getOrderData()
            orderNum = orderData?.order_num
        }
        if (orderActivity == null || orderNum == null || orderData == null) return
        when (title) {
            "评价" -> {
                var intent = Intent(orderActivity, AddPinglunActivity::class.java)
                orderActivity?.startActivity(intent)
            }
            "确认收货" -> {
                UIUtils.showToast("确认收货")
            }
            "付款" -> {
//                UIUtils.showToast("付款")
                val dialog = OrderPayDialog.newInstance(PayDialogData(
                        orderData?.id ?: 0,
                        orderData?.order_num ?: "",
                        "",
                        orderData?.pay_amount ?: 0.00f
                ))
                dialog.show(orderActivity?.supportFragmentManager!!, "")
            }
            "取消订单" -> {
                val cancelDialog = CancelOrderDialog.newInstance(orderNum)
                cancelDialog.show(orderActivity!!.supportFragmentManager, "")
                cancelDialog.setOnDismiss {
                    orderActivity?.initData()
                }
            }
            "延长收货" -> {
                ycshDialog.show()
            }
            "修改地址" -> {
                var intent = Intent(context, ChangeAddressActivity::class.java)
                intent.putExtra("orderId", orderData?.id)
                intent.putExtra("address", OrderDetailAddress(
                        orderData?.address_id ?: 0,
                        orderData?.address?.recipient ?: "",
                        orderData?.address?.mobile,
                        orderData?.address?.country,
                        orderData?.address?.province,
                        orderData?.address?.city,
                        "",
                        orderData?.address?.detail
                ))
                orderActivity?.startActivity(intent)
            }
            "查看进度" -> {
                val intent = Intent(context, CancelRefundActivity::class.java)
                intent.putExtra("orderNum", orderNum)
                context.startActivity(intent)
            }
        }
    }

    /**
     * 延迟收货
     */
    private val ycshDialog: IosAlertDialog by lazy {
        val dialog = IosAlertDialog(context)
                .builder()
                .setTitle("确认延长收货时间？")
                .setHeightMsg("每笔订单只能延迟一次哦")
                .setPositiveButton("确认", Color.parseColor("#FF2942")) {
                    orderActivity?.mBinding?.vm?.postYcsh(orderNum!!)
                }
                .setNegativeButton("取消", Color.parseColor("#333333")) {

                }
        dialog
    }

    /**
     * 取消退款
     */
    private val cancelRefundDialog: IosAlertDialog by lazy {
        val dialog = IosAlertDialog(context)
                .builder()
                .setTitle("取消退款？")
                .setHeightMsg("每笔订单只能延迟一次哦")
                .setPositiveButton("确认", Color.parseColor("#FF2942")) {
                    orderActivity?.mBinding?.vm?.cancelRefund(orderNum!!)
                }
                .setNegativeButton("取消", Color.parseColor("#333333")) {

                }
        dialog
    }
}