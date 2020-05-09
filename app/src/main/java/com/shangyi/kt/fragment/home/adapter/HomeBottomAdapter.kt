package com.shangyi.kt.fragment.home.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.fragment.home.model.DisCountListBean
import com.shangyi.kt.fragment.home.model.HomeDataBean
import com.shangyi.kt.ui.mine.weight.HomeYhqItemView
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
        holder.itemView.flowLayout.removeAllViewsInLayout()
        item?.discountList?.forEach {
            val item = HomeYhqItemView(context)
            item.text = getYouhuiquanStr(it)
            holder.itemView.flowLayout.addView(item)
        }
        if (!item?.discountList.isNullOrEmpty()) {
            holder.itemView.flowLayout.visibility = View.VISIBLE
        } else {
            holder.itemView.flowLayout.visibility = View.GONE
        }
    }

    /**
     * 获取代金券的描述信息
     */
    fun getYouhuiquanStr(bean: DisCountListBean): String {
        return when (bean.type) {
            1 -> {  // 满减
                "${"领券满" + bean.full_price + "减" + bean.price}"
            }
            2 -> {   // 代金券
                "${"领券立减" + bean.price}"
            }
            3 -> {
                "兑换券"
            }
            else -> ""
        }
    }
}