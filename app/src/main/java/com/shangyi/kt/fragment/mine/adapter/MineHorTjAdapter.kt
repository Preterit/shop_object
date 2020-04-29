package com.shangyi.kt.fragment.mine.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import kotlinx.android.synthetic.main.item_mine_hor_tj.view.*


/**
 * Date:2020/4/29
 * author:lwb
 * Desc:
 */
class MineHorTjAdapter constructor(val selectedColor: Int, val normalColor: Int) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_mine_hor_tj) {
    private var currentSelectItem = 0  // 选中的条目

    override fun convert(holder: BaseViewHolder, item: String) {

        holder.itemView.tvTitle.text = item
        holder.itemView.tvTitle.paint.isFakeBoldText = currentSelectItem == holder.layoutPosition  //加粗

        if (holder.layoutPosition == currentSelectItem) {
            holder.itemView.tvLine.visibility = View.VISIBLE
            holder.itemView.tvTitle.setTextColor(selectedColor)
        } else {
            holder.itemView.tvLine.visibility = View.INVISIBLE
            holder.itemView.tvTitle.setTextColor(normalColor)
        }

        holder.itemView.setOnClickListener {
            currentSelectItem = holder.layoutPosition
            notifyDataSetChanged()
            mListener?.itemSelect(holder.layoutPosition)
        }
    }

    fun setItemSelect(position: Int) {
        this.currentSelectItem = position
        notifyDataSetChanged()
    }

    private var mListener: OnItemScroClick? = null
    fun setOnItemScroClick(listener: OnItemScroClick) {
        this.mListener = listener
    }

    interface OnItemScroClick {
        fun itemSelect(position: Int)
    }
}