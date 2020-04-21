package com.shangyi.kt.ui.pingjia

/**
 * Date:2020/4/21
 * author:lwb
 * Desc:
 */
data class OrderBean(
    val count: Int,
    val dealer: Dealer,
    val goods_one_img: GoodsOneImg,
    val id: Int,
    val intro: String,
    val msales: Int,
    val name: String,
    val price: Double,
    val sale_count: Int,
    val sale_price: Double,
    val shop_id: Int,
    val unit_id: Int,
    val weight: Double
)

data class Dealer(
    val cash_back: Int,
    val dealer: Int,
    val gid: Int
)

data class GoodsOneImg(
    val good_id: Int,
    val url: String
)