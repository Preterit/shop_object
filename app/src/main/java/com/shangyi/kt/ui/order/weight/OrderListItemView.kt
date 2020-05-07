package com.shangyi.kt.ui.order.weight

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.shangyi.business.R
import com.shangyi.kt.ui.mine.bean.OrderGood
import kotlinx.android.synthetic.main.item_order_list.view.*

/**
 * Date:2020/4/24
 * author:lwb
 * Desc:
 */
class OrderListItemView : LinearLayout {
    constructor(context: Context) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.item_order_list, this)
    }

    fun setData(data: OrderGood?) {
        glideImageView.loadImage(data?.pic ?: "")
        tvGoodsName.text = data?.good_name
        tvGoodsSpec.text = data?.sku_info
        tvGoodsPrice.text = data?.price.toString()
        tvGoodsCount.text = "x${data?.number}"
    }
}