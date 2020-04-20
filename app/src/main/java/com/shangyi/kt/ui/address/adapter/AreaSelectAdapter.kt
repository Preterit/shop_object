package com.shangyi.kt.ui.address.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.address.bean.AreaItemBean
import kotlinx.android.synthetic.main.item_only_text.view.*

/**
 * Date:2020/4/20
 * author:lwb
 * Desc:
 */
class AreaSelectAdapter : BaseQuickAdapter<AreaItemBean, BaseViewHolder>(R.layout.item_only_text) {
    init {
        val list = arrayListOf<String>("", "", "", "", "", "", "", "", "", "")
//        setList(list)
    }

    override fun convert(holder: BaseViewHolder, item: AreaItemBean) {
        holder.itemView.txAddress.text = item.name
    }
}