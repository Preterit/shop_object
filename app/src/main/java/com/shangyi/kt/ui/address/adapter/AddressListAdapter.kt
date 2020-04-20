package com.shangyi.kt.ui.address.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R

/**
 * Date:2020/4/19
 * author:lwb
 * Desc:
 */
class AddressListAdapter:BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_address_list) {
    init {
        val list = arrayListOf<String>(
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        )
        setList(list)
    }
    override fun convert(holder: BaseViewHolder, item: String) {

    }
}