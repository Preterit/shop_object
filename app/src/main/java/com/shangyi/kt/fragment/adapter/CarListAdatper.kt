package com.shangyi.kt.fragment.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import kotlinx.android.synthetic.main.item_car_shop_layout.view.*

/**
 * Date:2020/4/22
 * author:lwb
 * Desc:
 */
class CarListAdatper : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_car_shop_layout) {

    init {
        setList(arrayListOf("", "", "", "", ""))
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.itemView.recyclerview.layoutManager = LinearLayoutManager(context)
        holder.itemView.recyclerview.adapter = CarGoodsItemAdapter()
    }
}