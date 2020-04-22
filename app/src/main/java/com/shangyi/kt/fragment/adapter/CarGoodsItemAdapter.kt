package com.shangyi.kt.fragment.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import kotlinx.android.synthetic.main.item_car_goods_view.view.*

/**
 * Date:2020/4/22
 * author:lwb
 * Desc:
 */
class CarGoodsItemAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_car_goods_view) {
    init {
        setList(arrayListOf("", ""))
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.glideImageView.loadImage("", R.color.placeholder_color)
    }
}