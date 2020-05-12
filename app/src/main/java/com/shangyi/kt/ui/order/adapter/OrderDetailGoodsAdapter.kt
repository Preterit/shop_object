package com.shangyi.kt.ui.order.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.order.bean.OrderDetailGood
import kotlinx.android.synthetic.main.item_order_detail_goods.view.*

/**
 * Date:2020/5/11
 * author:lwb
 * Desc:
 */
class OrderDetailGoodsAdapter : BaseQuickAdapter<OrderDetailGood, BaseViewHolder>(R.layout.item_order_detail_goods) {

    override fun convert(holder: BaseViewHolder, item: OrderDetailGood) {
        holder.itemView.glideImageView.loadImage(item.pic ?: "")
        holder.itemView.tvGoodsName.text = item.good_name ?: ""
        holder.itemView.tvGoodsSpec.text = item.sku_info ?: ""
        holder.itemView.tvGoodsPrice.text = "${item.price}"
        holder.itemView.tvGoodsCount.text = "x${item.number}"
    }

}