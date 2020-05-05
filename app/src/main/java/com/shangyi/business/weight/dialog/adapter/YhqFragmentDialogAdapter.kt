package com.shangyi.business.weight.dialog.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R

/**
 * Date:2020/5/5
 * author:lwb
 * Desc:
 */
class YhqFragmentDialogAdapter :BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_yhq_fragment_dialog) {
    init {
        setList(arrayListOf("","","","","","",""))
    }
    override fun convert(holder: BaseViewHolder, item: String) {

    }
}