package com.shangyi.kt.ui.mine.order.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.business.weight.dialog.CancelOrderDialog
import com.shangyi.business.weight.dialog.IosAlertDialog
import com.shangyi.kt.ui.mine.bean.OrderListBean
import com.shangyi.kt.ui.mine.order.ChangeAddressActivity
import com.shangyi.kt.ui.mine.order.OrderListFragment
import com.shangyi.kt.ui.order.OrderDetailActivity
import com.shangyi.kt.ui.order.weight.OrderListItemView
import kotlinx.android.synthetic.main.order_list_fragment_item.view.*

/**
 * Date:2020/5/6
 * author:lwb
 * Desc:
 */
class OrderListFragmentAdapter constructor(private val fragment: OrderListFragment) : BaseQuickAdapter<OrderListBean, BaseViewHolder>(R.layout.order_list_fragment_item) {

    private var orderNum = ""
    companion object {
        const val ORDER_LIST_ID_BUNDLE_KEY = "orderListId";
    }

    override fun convert(holder: BaseViewHolder, item: OrderListBean) {
        holder.itemView.tvShopName.text = item.shop?.name
        holder.itemView.ivShopPhoto.loadImage(item.shop?.shop_avatar ?: "")
        holder.itemView.tvPrice.text = "${item.pay_amount}"
        holder.itemView.tvGoodsCountStr.text = "共${item.order_goods.size}件商品 合计：¥ "
        holder.itemView.tvFanPrice.text = item.commission
        holder.itemView.tvStatusStr.text = item.order_status
        holder.itemView.order_list_item_layout.setOnClickListener({
            val intent = Intent();
            intent.setClass(context, OrderDetailActivity::class.java);
            val bundle = Bundle();
            bundle.putInt(ORDER_LIST_ID_BUNDLE_KEY, item.id);
            intent.putExtras(bundle);
            (context as Activity).startActivity(intent);
        });

        /**
         * 备注信息
         */
        if (item.remark.isNullOrEmpty()) {
            holder.itemView.tvPS.visibility = View.GONE
            holder.itemView.editPs.visibility = View.GONE
        } else {
            holder.itemView.editPs.text = item.remark
            holder.itemView.tvPS.visibility = View.VISIBLE
            holder.itemView.editPs.visibility = View.VISIBLE
        }

        holder.itemView.goodsLayout.removeAllViews()
        item.order_goods?.forEach {
            var orderGoodsItemView = OrderListItemView(context)
            orderGoodsItemView.setData(it)
            holder.itemView.goodsLayout.addView(orderGoodsItemView)
        }

        setBtnStatus(item, holder)
        setBtnClickListener(item, holder)
//        addChildClickViewIds(R.id.btn1,R.id.btn2,R.id.btn3)
    }

    /**
     * 设置btn点击事件
     */
    private fun setBtnClickListener(item: OrderListBean, holder: BaseViewHolder) {
        holder.itemView.btn1.setOnClickListener {
            when (item.status) {
                0 -> {
                    Toast.makeText(context, "付款", Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    Toast.makeText(context, "确认收货", Toast.LENGTH_SHORT).show()
                }
                3 -> {
                    Toast.makeText(context, "评价", Toast.LENGTH_SHORT).show()
                }
            }
        }
        holder.itemView.btn2.setOnClickListener {
            when (item.status) {
                0 -> {
//                    Toast.makeText(context, "取消订单", Toast.LENGTH_SHORT).show()
                    val cancelDialog = CancelOrderDialog.newInstance(item.order_num)
                    cancelDialog.show(fragment.childFragmentManager, "")
                    cancelDialog.setOnDismiss {
                        fragment.initData()
                    }
                }
                2, 3 -> {
                    Toast.makeText(context, "查看物流", Toast.LENGTH_SHORT).show()
                }
            }
        }
        holder.itemView.btn3.setOnClickListener {
            when (item.status) {
                0 -> {  // 待支付
//                    Toast.makeText(context, "修改地址", Toast.LENGTH_SHORT).show()
                    var intent = Intent(context, ChangeAddressActivity::class.java)
                    intent.putExtra("orderNum",orderNum)
                    context.startActivity(intent)
                }
                2 -> {  // 待收货
//                    Toast.makeText(context, "延长收货", Toast.LENGTH_SHORT).show()
                    orderNum = item.order_num
                    ycshDialog.show()
                }
            }
        }
    }
    //订单状态 0、待支付 1、支付成功（待发货） 2、已发货（待收货） 3、已签收 4、订单取消（已取消） 5、订单删除（已删除） 6、申请退款 7、已退款、 null取全部
    /**
     * 设置btn状态
     */
    private fun setBtnStatus(item: OrderListBean, holder: BaseViewHolder) {
        when (item.status) {
            0 -> {
                // 待支付
                holder.itemView.btnLayout.visibility = View.VISIBLE
                holder.itemView.btn1.visibility = View.VISIBLE
                holder.itemView.btn2.visibility = View.VISIBLE
                holder.itemView.btn3.visibility = View.VISIBLE
                holder.itemView.btn1.text = "付款"
                holder.itemView.btn2.text = "取消订单"
                holder.itemView.btn3.text = "修改地址"
            }
            1 -> {
                // 待发货
                holder.itemView.btnLayout.visibility = View.GONE
            }
            2 -> {
                // 待收货
                holder.itemView.btnLayout.visibility = View.VISIBLE
                holder.itemView.btn1.visibility = View.VISIBLE
                holder.itemView.btn2.visibility = View.VISIBLE
                holder.itemView.btn3.visibility = View.VISIBLE
                holder.itemView.btn1.text = "确认收货"
                holder.itemView.btn2.text = "查看物流"
                holder.itemView.btn3.text = "延长收货"
            }
            3 -> {
                // 待评价
                holder.itemView.btnLayout.visibility = View.VISIBLE
                holder.itemView.btn1.visibility = View.VISIBLE
                holder.itemView.btn2.visibility = View.VISIBLE
                holder.itemView.btn3.visibility = View.GONE
                holder.itemView.btn1.text = "评价"
                holder.itemView.btn2.text = "查看物流"
            }
            else -> {
                holder.itemView.btnLayout.visibility = View.GONE
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
                    fragment.mBinding.vm?.postYcsh(orderNum)
                }
                .setNegativeButton("取消", Color.parseColor("#333333")) {

                }
        dialog
    }
}