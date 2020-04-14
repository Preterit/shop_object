package com.shangyi.kt.ui.goods.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.goods.bean.ReecommendGood
import kotlinx.android.synthetic.main.item_goodsdetail_lookmore.view.*

/**
 * Date:2020/4/10
 * author:lwb
 * Desc:
 */
class GoodsDetailLookmoreAdapter constructor(layoutRes: Int? = R.layout.item_goodsdetail_lookmore) :
        BaseQuickAdapter<ReecommendGood, BaseViewHolder>(layoutRes!!) {

    override fun convert(holder: BaseViewHolder, item: ReecommendGood) {
        holder.itemView.glideImageView.loadImage(item.unit ?: "", R.color.placeholder_color)
        holder.itemView.tvTitle.text = item.name
        holder.itemView.tvPrice.text = item.sale_price.toString()
    }
}