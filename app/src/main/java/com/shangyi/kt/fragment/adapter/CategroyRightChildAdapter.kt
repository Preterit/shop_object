package com.shangyi.kt.fragment.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.fragment.bean.ChildItemBean
import kotlinx.android.synthetic.main.item_categroy_right_child.view.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CategroyRightChildAdapter constructor(data: List<ChildItemBean>?) :
        BaseQuickAdapter<ChildItemBean?, BaseViewHolder>(R.layout.item_categroy_right_child, ArrayList<ChildItemBean?>(data)) {

    init {
        val list = arrayListOf<String>(
                "个护关", "清洁管", "进口清洁",
                "卫生棉条", "湿厕纸", "走路/止汗珠",
                "免洗洗手液", "趣闻用品")
    }

    override fun convert(holder: BaseViewHolder, item: ChildItemBean?) {
        holder.itemView.tvTitle.text = item?.name
        Glide.with(context).load(item?.category_img?.img).into(holder.itemView.ivImg)
    }
}