package com.shangyi.kt.ui.goods.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.goods.bean.GoodsAttribute
import kotlinx.android.synthetic.main.item_goods_attr_view.view.*

/**
 * Date:2020/4/15
 * author:lwb
 * Desc:
 */
class AttriAdapter constructor(val whitColor: Int, val color_F8F8F8: Int) : BaseQuickAdapter<GoodsAttribute?, BaseViewHolder>(R.layout.item_goods_attr_view) {

    override fun convert(holder: BaseViewHolder, item: GoodsAttribute?) {
        if (holder.layoutPosition % 2 != 0) {
            holder.itemView.parentLayout.setBackgroundColor(whitColor)
        } else {
            holder.itemView.parentLayout.setBackgroundColor(color_F8F8F8)
        }
        holder.itemView.tvAttrLeft.text = item?.name
        holder.itemView.tvAttrRight.text = item?.value
    }
}