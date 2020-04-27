package com.shangyi.kt.fragment.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.fragment.bean.MineImgTxBean
import kotlinx.android.synthetic.main.item_mine_order_center.view.*

/**
 * Date:2020/4/27
 * author:lwb
 * Desc:
 */
class MineVipCenterAdapter constructor(data: ArrayList<MineImgTxBean>)
    : BaseQuickAdapter<MineImgTxBean, BaseViewHolder>(R.layout.item_mine_vip_center, data) {


    override fun convert(holder: BaseViewHolder, item: MineImgTxBean) {
        holder.itemView.tvTitle.text = item.title
        holder.itemView.ivOrderCenter.setImageResource(item.img)
    }
}