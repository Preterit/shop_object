package com.shangyi.kt.ui.pingjia.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import kotlinx.android.synthetic.main.item_pingjia_selecter_view.view.*

/**
 * Date:2020/4/21
 * author:lwb
 * Desc:
 */
class PingjiaSelecterAdapter constructor(private val selectColor: Int, private val normalColor: Int) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_pingjia_selecter_view) {

    init {
        setList(arrayListOf<String>("全部 (29万+) ", "全部 (29万+) ", "全部 (29万+) ", "全部 (29万+) ", "全部 (29万+) "))
    }

    private var currentItem = 0
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.tvStr.text = item
        if (currentItem == holder.layoutPosition) {
            holder.itemView.tvStr.setBackgroundResource(R.drawable.shape_pinglun_select_bg)
            holder.itemView.tvStr.setTextColor(selectColor)
        } else {
            holder.itemView.tvStr.setBackgroundResource(R.drawable.shape_pinglun_unselect_bg)
            holder.itemView.tvStr.setTextColor(normalColor)
        }
    }
}