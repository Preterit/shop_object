package com.shangyi.kt.fragment.mine.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import kotlinx.android.synthetic.main.item_mine_bottom_layout.view.*

/**
 * Date:2020/4/28
 * author:lwb
 * Desc:
 */
class BottomAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_mine_bottom_layout) {

    init {
        setList(arrayListOf("", "", "", "", "", "", "", "", "", "", ""))
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.tvItem.text = "第${holder.layoutPosition}个条目"
    }
}