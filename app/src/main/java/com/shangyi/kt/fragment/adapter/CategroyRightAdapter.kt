package com.shangyi.kt.fragment.adapter

import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.fragment.bean.ChildBean
import kotlinx.android.synthetic.main.item_categroy_right.view.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CategroyRightAdapter : BaseQuickAdapter<ChildBean, BaseViewHolder> {

    constructor() : super(R.layout.item_categroy_right) {}

    override fun convert(holder: BaseViewHolder, item: ChildBean) {
        holder.itemView.tvTitle.text = item.name

        holder.itemView.recyclerview.layoutManager = GridLayoutManager(context, 3)
        holder.itemView.recyclerview.adapter = CategroyRightChildAdapter(item.child_list)
    }
}