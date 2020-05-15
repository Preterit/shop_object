package com.shangyi.business.weight.dialog.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import kotlinx.android.synthetic.main.item_order_cancel_view.view.*

/**
 * Date:2020/5/6
 * author:lwb
 * Desc:
 */
class RefundOrderAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_order_cancel_view) {

    private var selectItemPosition = -1

    init {
        setList(arrayListOf("订单信息拍错（规格/尺码/颜色等）", "我不想要了", "地址/电话信息填写错误", "没用/少用优惠", "缺货"))
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.checkbox.isEnabled = selectItemPosition == holder.layoutPosition
        holder.itemView.tvTitle.text = item
    }

    fun setCurrentSelectItem(position: Int) {
        this.selectItemPosition = position
        notifyDataSetChanged()
    }
}