package com.shangyi.business.weight.dialog.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.goods.bean.YouhuiquanBean
import kotlinx.android.synthetic.main.item_goods_yhq_dialog.view.*

/**
 * Date:2020/5/5
 * author:lwb
 * Desc:
 */
class GoodsYhqDialogAdapter : BaseQuickAdapter<YouhuiquanBean, BaseViewHolder>(R.layout.item_goods_yhq_dialog) {

    override fun convert(holder: BaseViewHolder, item: YouhuiquanBean) {
        holder.itemView.tvPrice.text = item.price.toInt().toString()
        holder.itemView.tvTerm.text = getYhqStr(item)
        holder.itemView.tvMj.text = getYouhuiquanStr(item)
        holder.itemView.tvTime.text = "${item.start_time} -\n${item.end_time}"
        holder.itemView.tvLq.setOnClickListener {
            mListener?.getYhqClick(item.id)
        }
    }

    /**
     * 获取代金券的描述信息
     */
    fun getYouhuiquanStr(bean: YouhuiquanBean): String {
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

    fun getYhqStr(bean: YouhuiquanBean): String {
        return when (bean.type) {
            1 -> {  // 满减
                "满${bean.full_price}可用"
            }
            2, 3 -> {   // 代金券
                "无限制"
            }

            else -> ""
        }
    }

    interface OnGetClickListener {
        fun getYhqClick(id: Int)
    }

    private var mListener: OnGetClickListener? = null

    fun setOnGetClickListener(listener: OnGetClickListener) {
        this.mListener = listener
    }
}