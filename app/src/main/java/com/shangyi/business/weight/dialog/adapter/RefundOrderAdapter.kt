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
        setList(arrayListOf("我不想买了", "信息填写错误，重新拍", "卖家缺货", "同城见面交易", "其他原因"))
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.checkbox.isEnabled = selectItemPosition == holder.layoutPosition
        holder.itemView.tvTitle.text = item
    }

    fun setCurrentSelectItem(position:Int){
        this.selectItemPosition = position
        notifyDataSetChanged()
    }
}