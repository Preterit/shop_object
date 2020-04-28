package com.shangyi.kt.fragment.other.lookmore.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.goods.bean.ReecommendGood
import kotlinx.android.synthetic.main.item_goods_look_more.view.*

/**
 * Date:2020/4/26
 * author:lwb
 * Desc:
 */
class GoodsLookMoreAdapter : BaseQuickAdapter<ReecommendGood?, BaseViewHolder>(R.layout.item_goods_look_more) {

    override fun convert(holder: BaseViewHolder, item: ReecommendGood?) {
        if (item == null) return
        holder.itemView.glideImageView.loadImage(item.goods_one_img?.url ?: "")
        holder.itemView.tvTitle.text = item.name
        holder.itemView.tvPrice.text = item.sale_price.toString()
        holder.itemView.tvZhuanTx.text = "赚${item.dealer?.dealer}"
    }

}