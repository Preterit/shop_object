package com.shangyi.kt.fragment.adapter

import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.business.utils.dp2px
import com.shangyi.business.weight.GridDividerItemDecoration
import kotlinx.android.synthetic.main.item_categroy_right.view.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CategroyRightAdapter : BaseQuickAdapter<String, BaseViewHolder> {

    var height = 5.dp2px()
    constructor() : super(R.layout.item_categroy_right) {
        val list = arrayListOf<String>(
                "常用分类", "热门频道", "推荐活动",
                "卫生棉条", "湿厕纸", "走路/止汗珠",
                "免洗洗手液", "趣闻用品")
        addData(list)
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.recyclerview.layoutManager = GridLayoutManager(context, 3)
        holder.itemView.recyclerview.adapter = CategroyRightChildAdapter()
        holder.itemView.recyclerview.addItemDecoration(GridDividerItemDecoration(height, height))
    }
}