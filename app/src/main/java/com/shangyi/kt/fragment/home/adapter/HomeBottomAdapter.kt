package com.shangyi.kt.fragment.home.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.fragment.home.model.HomeDataBean
import kotlinx.android.synthetic.main.item_home_list_view.view.*

/**
 * Date:2020/5/7
 * author:lwb
 * Desc:
 */
class HomeBottomAdapter : BaseQuickAdapter<HomeDataBean?, BaseViewHolder>(R.layout.item_home_list_view) {

    override fun convert(holder: BaseViewHolder, item: HomeDataBean?) {
        holder.itemView.glideImageView.loadImage(item?.goods_one_img?.url)
        holder.itemView.tvTitle.text = item?.name
        holder.itemView.tvPrice.text = "¥${item?.sale_price}"
        holder.itemView.tvShouNum.text = "已售${item?.sale_count}"
        holder.itemView.tvZhuanTx.text = "${item?.dealer?.cash_back ?: 0.00}"

        /**
         * 优惠券
         */
        //TODO 优惠券
    }
}