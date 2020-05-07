package com.shangyi.kt.ui.mine.bean

/**
 * Date:2020/5/6
 * author:lwb
 * Desc:
 */
data class OrderListBean(
    val commission: String,
    val id: Int,
    val order_goods: List<OrderGood?>,
    val order_num: String,
    val order_status: String,
    val order_type: Int,
    val pay_amount: Double,
    val remark: String,
    val shop: Shop?,
    val status: Int
)

data class OrderGood(
    val gid: Int,
    val good_name: String,
    val number: Int,
    val oid: Int,
    val pic: String?,
    val price: Float,
    val sku_info: String
)

data class Shop(
    val id: Int,
    val name: String,
    val shop_avatar: String?
)