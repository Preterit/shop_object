package com.shangyi.kt.ui.order.bean

/**
 * Date:2020/5/11
 * author:lwb
 * Desc:
 */
data class OrderDetailInfoBean(
        val address: Address,
        val address_id: Int,
        val commission: String,
        val coupon_price: Int,
        val create_time: String,
        val freight: String,
        val id: Int,
        val order_goods: List<OrderDetailGood>,
        val order_num: String,
        val order_status: String,
        val order_type: Int,
        val pay_amount: Float,
        val pay_time: Any,
        val remark: String?,
        val send_time: Any,
        val status: Int,
        val shop: ShopInfo?,
        val total_amount: Float
)

data class Address(
        val city: String,
        val country: String,
        val detail: String,
        val id: Int,
        val mobile: String,
        val province: String,
        val recipient: String,
        val zip_code: Any
)

data class OrderDetailGood(
        val gid: Int,
        val good_name: String?,
        val number: Int,
        val oid: Int,
        val pic: String?,
        val price: Float,
        val sku_info: String?
)

data class ShopInfo(
        val id: Int,
        val name: String,
        val shop_avatar: String?
)