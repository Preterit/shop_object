package com.shangyi.kt.ui.goods.adapter

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.goods.GoodsDetailActivity
import com.shangyi.kt.ui.goods.bean.GoodsListBean
import kotlinx.android.synthetic.main.item_goods_list.view.*

/**
 * Date:2020/4/10
 * author:lwb
 * Desc:
 */
class GoodsListAdapter : BaseQuickAdapter<GoodsListBean, BaseViewHolder>(R.layout.item_goods_list) {

    override fun convert(holder: BaseViewHolder, item: GoodsListBean) {

        holder.itemView.tvTitle.text = item.name
        holder.itemView.tvPrice.text = item.price.toString()
        holder.itemView.tvPjNum.text = "${item.praise_count}+好评"
        holder.itemView.tvPjBfb.text = "${item.praise_count / item.comment_count}%好评"
        holder.itemView.glideImageView.loadImage(item.goods_img[0]?.url
                ?: "", R.color.placeholder_color)


        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, GoodsDetailActivity::class.java))
        }
    }
}