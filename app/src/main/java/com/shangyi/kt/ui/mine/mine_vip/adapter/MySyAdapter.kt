package com.shangyi.kt.ui.mine.mine_vip.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.mine.bean.ItemBean
import com.shangyi.kt.ui.mine.weight.MySyTextCusView
import kotlinx.android.synthetic.main.cum_order_yhq_layout.view.*
import kotlinx.android.synthetic.main.item_my_shouyi.view.*

/**
 * Date:2020/5/11
 * author:lwb
 * Desc:
 */
class MySyAdapter : BaseQuickAdapter<ItemBean, BaseViewHolder>(R.layout.item_my_shouyi) {

    override fun convert(holder: BaseViewHolder, item: ItemBean) {
        holder.itemView.tvLeftText.text = item.time
        holder.itemView.tvRightText.text = "总收入：￥${item.total}"

        holder.itemView.itemLayout.removeAllViews()
        item.list.forEachIndexed { index, childItemBean ->
            var mySyTextCusView = MySyTextCusView(context)
            mySyTextCusView.setData(childItemBean, index != item.list.size - 1)
            holder.itemView.itemLayout.addView(mySyTextCusView)
        }
    }
}