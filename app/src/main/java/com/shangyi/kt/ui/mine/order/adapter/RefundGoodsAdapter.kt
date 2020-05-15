package com.shangyi.kt.ui.mine.order.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.order.bean.OrderDetailGood
import kotlinx.android.synthetic.main.item_refund_layout.view.*

/**
 * Date:2020/5/14
 * author:lwb
 * Desc:
 */
class RefundGoodsAdapter : BaseQuickAdapter<OrderDetailGood, BaseViewHolder>(R.layout.item_refund_layout) {
    override fun convert(holder: BaseViewHolder, item: OrderDetailGood) {
        holder.itemView.glideImageView.loadImage(item.pic)
        holder.itemView.tvName.text = item.good_name
        holder.itemView.tvAttr.text = item.sku_info
    }
}