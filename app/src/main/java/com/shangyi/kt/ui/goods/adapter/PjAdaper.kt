package com.shangyi.kt.ui.goods.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.goods.bean.AppraiseInfoBean
import kotlinx.android.synthetic.main.item_shopinfo_pj.view.*

/**
 * Date:2020/4/14
 * author:lwb
 * Desc:
 */
class PjAdaper : BaseQuickAdapter<AppraiseInfoBean, BaseViewHolder>(R.layout.item_shopinfo_pj) {
    override fun convert(holder: BaseViewHolder, item: AppraiseInfoBean) {
        holder.itemView.ivUserPhoto.loadImage(item.user?.avatar, R.color.placeholder_color)
        holder.itemView.tvUserName.text = item.user?.nickname
        holder.itemView.tvPjContent.text = item.content
    }
}