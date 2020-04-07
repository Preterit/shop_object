package com.shangyi.kt.fragment.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import kotlinx.android.synthetic.main.item_categroy_right_child.view.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CategroyRightChildAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_categroy_right_child) {

    init {
        val list = arrayListOf<String>(
                "个护关", "清洁管", "进口清洁",
                "卫生棉条", "湿厕纸", "走路/止汗珠",
                "免洗洗手液", "趣闻用品")
        addData(list)
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.tvTitle.text = item
    }
}