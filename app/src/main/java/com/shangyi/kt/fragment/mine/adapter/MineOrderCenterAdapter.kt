package com.shangyi.kt.fragment.mine.adapter

import android.content.Intent
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.fragment.mine.bean.MineImgTxBean
import com.shangyi.kt.ui.mine.order.AfterSaleActivity
import com.shangyi.kt.ui.mine.order.AllOrderActivity
import kotlinx.android.synthetic.main.item_mine_order_center.view.*

/**
 * Date:2020/4/27
 * author:lwb
 * Desc:
 */
class MineOrderCenterAdapter constructor(data: ArrayList<MineImgTxBean>)
    : BaseQuickAdapter<MineImgTxBean, BaseViewHolder>(R.layout.item_mine_order_center, data) {


    override fun convert(holder: BaseViewHolder, item: MineImgTxBean) {
        holder.itemView.tvTitle.text = item.title
        holder.itemView.ivOrderCenter.setImageResource(item.img)
        holder.itemView.setOnClickListener {
            val intent: Intent? = Intent(context, AllOrderActivity::class.java)
            when (item.title) {
                "待付款" -> {
                    intent?.putExtra("currentItem", 1)
                }
                "待发货" -> {
                    intent?.putExtra("currentItem", 2)
                }
                "待收货" -> {
                    intent?.putExtra("currentItem", 3)
                }
                "待评价" -> {
                    intent?.putExtra("currentItem", 4)
                }
                "退款/售后" -> {
                    context.startActivity(Intent(context, AfterSaleActivity::class.java))
                    Toast.makeText(context, "退款/售后", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            if (null != intent) {
                context.startActivity(intent)
            }
        }
    }
}