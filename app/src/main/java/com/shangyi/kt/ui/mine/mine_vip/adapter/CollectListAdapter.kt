package com.shangyi.kt.ui.mine.mine_vip.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.mine.bean.CollectListBean
import com.shangyi.kt.ui.mine.bean.Good
import com.shangyi.kt.ui.order.weight.CollectListItemView
import kotlinx.android.synthetic.main.item_collect_view.view.*

/**
 * Date:2020/5/7
 * author:lwb
 * Desc:
 */
class CollectListAdapter : BaseQuickAdapter<CollectListBean?, BaseViewHolder>(R.layout.item_collect_view), CollectListItemView.OnGoodsDelete {

    override fun convert(holder: BaseViewHolder, item: CollectListBean?) {
        holder.itemView.ivShopIcon.loadImage(item?.shop_avatar ?: "")
        holder.itemView.tvShopName.text = item?.shop_name

        holder.itemView.llGoodsLayout.removeAllViews()
        item?.goods?.forEach {
            val goodItem = CollectListItemView(context)
            goodItem.setData(it)
            goodItem.setOnGoodsDeleteListener(this)
            holder.itemView.llGoodsLayout.addView(goodItem)
        }

        holder.itemView.checkboxLayoutParent.visibility = if (item?.isEdit!!) View.VISIBLE else View.GONE
        holder.itemView.checkboxParent.isEnabled = item.isSelect

        holder.itemView.checkboxLayoutParent.setOnClickListener {
            item.goods.forEach {
                it.isSelect = !item.isSelect
            }
            item.isSelect = !item.isSelect
            notifyDataSetChanged()
        }
    }

    /**
     * 删除收藏
     */
    override fun onDelete(good: Good) {
        var iterator = data.iterator()
        while (iterator.hasNext()) {
            var it = iterator.next()
            if (good in it?.goods!!) {
                it.goods.remove(good)
            }
            if (it.goods.size == 0) {
                iterator.remove()
            }
            notifyDataSetChanged()
        }
        mListener?.oneItemDel(arrayListOf(good))
    }

    interface OnItemDelect {
        fun oneItemDel(good: List<Good>)
    }

    private var mListener: OnItemDelect? = null

    fun setOnItemDelect(listener: OnItemDelect) {
        mListener = listener
    }
}