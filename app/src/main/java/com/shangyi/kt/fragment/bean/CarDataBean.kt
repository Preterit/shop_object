package com.shangyi.kt.fragment.bean

/**
 * Date:2020/4/22
 * author:lwb
 * Desc:
 */
data class CarDataBean(
    val child: List<Child>,
    val id: Int,
    val name: String,
    val shop_avatar: Any
)

data class Child(
    val cid: Int,
    val count: Int,
    val discountList: List<Any>,
    val goods_id: Int,
    val goods_unit: GoodsUnit,
    val name: String,
    val number: Int,
    val price: Int,
    val sale_price: Double,
    val spec: Spec
)

data class GoodsUnit(
    val id: Int,
    val name: String
)

data class Spec(
    val id: Int,
    val image: String,
    val market_price: String,
    val price: String,
    val stock: Int,
    val value: String
)