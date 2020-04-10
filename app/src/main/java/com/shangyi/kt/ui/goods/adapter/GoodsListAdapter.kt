package com.shangyi.kt.ui.goods.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R

/**
 * Date:2020/4/10
 * author:lwb
 * Desc:
 */
class GoodsListAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_goods_list) {

    init {
        val list = arrayListOf<String>("", "", "", "", "", "", "", "", "", "")
        replaceData(list)
    }

    override fun convert(holder: BaseViewHolder, item: String) {

    }
}