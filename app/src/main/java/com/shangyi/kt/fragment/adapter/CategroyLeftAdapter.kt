package com.shangyi.kt.fragment.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import kotlinx.android.synthetic.main.item_categroy_left.view.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CategroyLeftAdapter constructor(private val normalTxColor: Int, private val selectTxColor: Int) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_categroy_left) {

    init {
        var list = arrayListOf<String>(
                "推荐分类", "京东超市", "国际名牌", "奢侈品", "京东国际",
                "男装", "女装", "男鞋", "女鞋", "内衣配饰", "箱包手袋",
                "美妆护肤", "个护情节", "钟表珠宝", "电脑办公", "家用电器",
                "食品生鲜", "酒水饮料", "母婴童装", "玩具乐器", "医药保健",
                "运动户外", "家具厨具", "礼品鲜花", "宠物生活", "生活旅行",
                "图书文娱", "农资园艺", "特茶馆", "京东金融", "拍卖",
                "房产", "京东服务", "工业品")
        addData(list)
    }

    var currentItem = 0

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.tvTitle.text = item
        holder.itemView.tvTitle.paint.isFakeBoldText = currentItem == holder.layoutPosition  //加粗

        if (currentItem == holder.layoutPosition) {
            holder.itemView.tvTitle.setTextColor(selectTxColor)
            holder.itemView.tvRedLine.visibility = View.VISIBLE
            holder.itemView.tvTitle.textSize = 16.toFloat()
            holder.itemView.setBackgroundResource(R.drawable.categroy_left_item_select)
        } else {
            holder.itemView.tvRedLine.visibility = View.GONE
            holder.itemView.tvTitle.setTextColor(normalTxColor)
            holder.itemView.tvTitle.textSize = 14.toFloat()
            holder.itemView.setBackgroundResource(R.drawable.categroy_left_item_unselect)
        }

        holder.itemView.setOnClickListener {
            currentItem = holder.layoutPosition
            notifyDataSetChanged()
        }
    }
}