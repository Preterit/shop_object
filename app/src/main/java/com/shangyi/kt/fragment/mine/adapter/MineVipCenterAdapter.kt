package com.shangyi.kt.fragment.mine.adapter

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.mine.yijian.YiJianActivity
import com.shangyi.kt.fragment.mine.bean.MineImgTxBean
import com.shangyi.kt.ui.address.AddressListActivity
import com.shangyi.kt.ui.mine.mine_vip.*
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
        holder.itemView.setOnClickListener {
            var intent: Intent? = null
            when (item.title) {
                "优惠券" -> {
                    intent = Intent(context, YhqActivity::class.java)
                }
                "收藏" -> {
                    intent = Intent(context, CollectActivity::class.java)
                }
                "分销管理" -> {
                    // intent = Intent(context, FenxiaoActivity::class.java)
                    //intent = Intent(context, FxManagerActivity::class.java)
                }
                "我的收益" -> {
                    intent = Intent(context, MySyActivity::class.java)
                }
                "地址管理" -> {
                    intent = Intent(context, AddressListActivity::class.java)
                }
                "意见反馈" -> {
                    intent = Intent(context, YiJianActivity::class.java)
//                    intent = Intent(context, FeedBackActivity::class.java)
                }
                "积分商城" -> {

                }
            }
            if (intent != null) {
                context.startActivity(intent)
            }
        }
    }
}