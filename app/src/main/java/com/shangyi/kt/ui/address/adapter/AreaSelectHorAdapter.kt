package com.shangyi.kt.ui.address.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import kotlinx.android.synthetic.main.item_area_hor_text.view.*
import kotlinx.android.synthetic.main.item_only_text.view.*
import kotlinx.android.synthetic.main.item_only_text.view.txAddress

/**
 * Date:2020/4/20
 * author:lwb
 * Desc:
 */
class AreaSelectHorAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_area_hor_text) {

    private var currentItem = 0

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.txAddress.text = item
        if (currentItem == holder.layoutPosition) {
            holder.itemView.tvLine.visibility = View.VISIBLE
        } else {
            holder.itemView.tvLine.visibility = View.GONE
        }
    }

    fun setSelectItem() {
        currentItem = data.size - 1
    }

    fun setSelectItem(str: String) {
        data.forEachIndexed { index, s ->
            if (str == s) currentItem = index
            notifyDataSetChanged()
            return@forEachIndexed
        }
    }
}