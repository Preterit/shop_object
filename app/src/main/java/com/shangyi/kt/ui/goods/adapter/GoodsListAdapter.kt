package com.shangyi.kt.ui.goods.adapter

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.goods.GoodsDetailActivity
import com.shangyi.kt.ui.goods.bean.GoodsListBean
import com.shangyi.kt.ui.goods.bean.YouhuiquanBean
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
        holder.itemView.tvZhuanTx.text = "¥ ${item.dealer?.cash_back}"
        if (item.discountList != null && item.discountList.isNotEmpty()) {
            holder.itemView.tvYhTx.visibility = View.VISIBLE
            holder.itemView.tvYhTx.text = getYouhuiquanStr(item.discountList[0])
        } else {
            holder.itemView.tvYhTx.visibility = View.GONE
        }
        if (item.comment_count == 0) {
            holder.itemView.tvPjBfb.text = "${item.comment_count}%好评"
        } else {
            holder.itemView.tvPjBfb.text = "${item.praise_count / item.comment_count}%好评"
        }

        holder.itemView.glideImageView.loadImage(item.goods_img[0]?.url
                ?: "", R.color.placeholder_color)


        holder.itemView.setOnClickListener {
            var intent = Intent(context, GoodsDetailActivity::class.java)
            intent.putExtra("goodsId", item.id)
            context.startActivity(intent)
        }
    }


    /**
     * 获取代金券的描述信息
     */
    fun getYouhuiquanStr(bean: YouhuiquanBean?): String {
        return when (bean?.type) {
            1 -> {  // 满减
                "${"领券满" + bean.full_price + "减" + bean.price}"
            }
            2 -> {   // 代金券
                "${"领券立减" + bean.price}"
            }
            else -> ""
        }
    }
}