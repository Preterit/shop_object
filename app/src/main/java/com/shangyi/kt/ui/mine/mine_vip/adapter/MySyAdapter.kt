package com.shangyi.kt.ui.mine.mine_vip.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.mine.weight.MySyTextCusView
import kotlinx.android.synthetic.main.item_my_shouyi.view.*

/**
 * Date:2020/5/11
 * author:lwb
 * Desc:
 */
class MySyAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_my_shouyi) {
    init {
        setList(arrayListOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.itemLayout.removeAllViews()
        holder.itemView.itemLayout.addView(MySyTextCusView(context))
        holder.itemView.itemLayout.addView(MySyTextCusView(context))
        holder.itemView.itemLayout.addView(MySyTextCusView(context))
    }
}