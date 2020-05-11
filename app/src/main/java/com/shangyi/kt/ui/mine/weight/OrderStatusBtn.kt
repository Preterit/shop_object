package com.shangyi.kt.ui.mine.weight

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.weight.dialog.CancelOrderDialog
import com.shangyi.business.weight.dialog.IosAlertDialog
import com.shangyi.kt.ui.mine.order.ChangeAddressActivity
import com.shangyi.kt.ui.mine.order.OrderDetailActivity
import com.shangyi.kt.ui.pingjia.AddPinglunActivity
import kotlinx.android.synthetic.main.activity_tx.view.*
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

    /**
     * 设置按钮点击事件
     */
    fun onItemClick(title: String) {
        if (context is OrderDetailActivity) {
            orderActivity = context as OrderDetailActivity
            orderNum = orderActivity?.getOrderNum()
        }
        if (orderActivity == null || orderNum == null) return
        when (title) {
            "评价" -> {
                var intent = Intent(orderActivity, AddPinglunActivity::class.java)
                orderActivity?.startActivity(intent)
            }
            "确认收货" -> {
                UIUtils.showToast("确认收货")
            }
            "付款" -> {
                UIUtils.showToast("付款")
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
                intent.putExtra("orderNum", orderNum)
                orderActivity?.startActivity(intent)
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
}