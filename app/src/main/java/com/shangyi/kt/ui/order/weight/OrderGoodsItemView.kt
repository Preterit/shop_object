package com.shangyi.kt.ui.order.weight

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.shangyi.business.R
import com.shangyi.kt.fragment.car.entity.GoodsInfoBean
import kotlinx.android.synthetic.main.item_order_goods.view.*

/**
 * Date:2020/4/24
 * author:lwb
 * Desc:
 */
class OrderGoodsItemView : LinearLayout {
    constructor(context: Context) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.item_order_goods, this)
    }

    fun setData(data: GoodsInfoBean?) {
        glideImageView.loadImage(data?.goodsImg ?: "", R.color.placeholder_color)
        tvGoodsName.text = data?.goodsName
        tvGoodsSpec.text = data?.SpecStr
        tvGoodsPrice.text = data?.goodsPrice.toString()
        tvGoodsCount.text = "x${data?.goodsCount}"
    }
}