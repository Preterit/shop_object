package com.shangyi.business.weight.dialog.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.order.bean.CommitOrderYhqData
import kotlinx.android.synthetic.main.item_yhq_fragment_dialog.view.*

/**
 * Date:2020/5/5
 * author:lwb
 * Desc:
 */
class YhqFragmentDialogAdapter : BaseQuickAdapter<CommitOrderYhqData, BaseViewHolder>(R.layout.item_yhq_fragment_dialog) {

    private val merchant_id = ""

    override fun convert(holder: BaseViewHolder, item: CommitOrderYhqData) {
        holder.itemView.tvPrice.text = item.price.toInt().toString()
        holder.itemView.tvTerm.text = getYhqStr(item)
        holder.itemView.tvMj.text = getYouhuiquanStr(item)
        if (item.can_use == 0) {
            holder.itemView.tvTime.text = "该优惠券不可用"
            holder.itemView.checkbox.visibility = View.GONE
        } else {
            holder.itemView.tvTime.text = ""
            holder.itemView.checkbox.visibility = View.VISIBLE
        }

        holder.itemView.checkbox.isEnabled = item.isSelect

        holder.itemView.setOnClickListener {
            if (item.merchant_id == "0") {  // 系统发放 优惠券
                if (item.isSelect) {
                    item.isSelect = false
                    notifyDataSetChanged()
                    return@setOnClickListener
                }
                clearSysYhq(item)
                return@setOnClickListener
            }
            if (item.isSelect) {   // 店铺发放的优惠券
                item.isSelect = false
                notifyDataSetChanged()
                return@setOnClickListener
            }
            setSelect(item)
            notifyDataSetChanged()
        }
    }

    /**
     * 清除 系统优惠券选中状态
     */
    fun clearSysYhq(item: CommitOrderYhqData) {
        data.forEach {
            if (it.merchant_id == "0") {
                it.isSelect = false
            }
        }
        item.isSelect = true
        notifyDataSetChanged()
    }

    /**
     * 刷新选中
     */
    private fun setSelect(item: CommitOrderYhqData) {
        data.forEach {
            if (item.merchant_id == "0") {
                it.isSelect = item.merchant_id == it.merchant_id
            } else {
                if (it.shop_id == item.shop_id) {
                    it.isSelect = item.id == it.id
                }
            }
        }
    }

    /**
     * 获取代金券的描述信息
     */
    fun getYouhuiquanStr(bean: CommitOrderYhqData): String {
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

    fun getYhqStr(bean: CommitOrderYhqData): String {
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

    /**
     * 获取选中的优惠券
     */
    fun getSelectData(): ArrayList<CommitOrderYhqData> {
        val list = arrayListOf<CommitOrderYhqData>()
        data.forEach {
            if (it.isSelect) {
                list.add(it)
            }
        }
        return list
    }
}