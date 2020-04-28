package com.shangyi.kt.fragment.categroy.adapter

import android.annotation.SuppressLint
import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.fragment.categroy.bean.ChildItemBean
import com.shangyi.kt.ui.goods.GoodsListActivity
import kotlinx.android.synthetic.main.item_categroy_right_child.view.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CategroyRightChildAdapter constructor(data: List<ChildItemBean>?) :
        BaseQuickAdapter<ChildItemBean?, BaseViewHolder>(R.layout.item_categroy_right_child, ArrayList<ChildItemBean?>(data)) {

    @SuppressLint("CheckResult")
    override fun convert(holder: BaseViewHolder, item: ChildItemBean?) {
        holder.itemView.tvTitle.text = item?.name
        holder.itemView.ivImg.loadImage(item?.image ?: "", R.color.placeholder_color)

        holder.itemView.ivImg.setOnClickListener {
            var intent = Intent(context, GoodsListActivity::class.java)
            intent.putExtra("categroyId", item?.id)
            context.startActivity(intent)
        }
    }
}