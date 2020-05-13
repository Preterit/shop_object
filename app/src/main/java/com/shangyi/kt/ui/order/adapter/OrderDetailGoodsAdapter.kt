package com.shangyi.kt.ui.order.adapter

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.mine.order.RefundActivity
import com.shangyi.kt.ui.order.bean.OrderDetailGood
import kotlinx.android.synthetic.main.item_order_detail_goods.view.*

/**
 * Date:2020/5/11
 * author:lwb
 * Desc:
 */
class OrderDetailGoodsAdapter : BaseQuickAdapter<OrderDetailGood, BaseViewHolder>(R.layout.item_order_detail_goods) {

    private var orderStatus = 0  // 订单状态
    private var mOrderNum = ""   // 订单编号

    override fun convert(holder: BaseViewHolder, item: OrderDetailGood) {
        holder.itemView.glideImageView.loadImage(item.pic ?: "")
        holder.itemView.tvGoodsName.text = item.good_name ?: ""
        holder.itemView.tvGoodsSpec.text = item.sku_info ?: ""
        holder.itemView.tvGoodsPrice.text = "${item.price}"
        holder.itemView.tvGoodsCount.text = "x${item.number}"

        // 1 : 支付成功
        // 2 : 已发货
        // 3 : 交易成功
        when (orderStatus) {
            1 -> {
                holder.itemView.tvBtn.visibility = View.VISIBLE
                holder.itemView.tvBtn.text = "退款"
            }
            2 -> {
                holder.itemView.tvBtn.visibility = View.VISIBLE
                holder.itemView.tvBtn.text = "退换"
            }
            3 -> {
                holder.itemView.tvBtn.visibility = View.VISIBLE
                holder.itemView.tvBtn.text = "申请售后"
            }
            else -> {
                holder.itemView.tvBtn.visibility = View.GONE
            }
        }

        holder.itemView.tvBtn.setOnClickListener {
            when (orderStatus) {
                1 -> {
                    val intent = Intent(context, RefundActivity::class.java)
                    intent.putExtra("goodsInfo", item)
                    intent.putExtra("order_num", mOrderNum)
                    context.startActivity(intent)
                }
                2 -> {
                    Toast.makeText(context, "退换", Toast.LENGTH_SHORT).show()
                }
                3 -> {
                    Toast.makeText(context, "申请售后", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun setOrderStatus(status: Int) {
        this.orderStatus = status
    }

    fun setOrderNum(orderNum: String) {
        this.mOrderNum = orderNum;
    }
}