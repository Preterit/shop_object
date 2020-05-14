package com.shangyi.kt.ui.mine.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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
        val rid: Int?,
        val pay_amount: Double,
        val remark: String,
        val shop: Shop?,
        val address: OrderDetailAddress?,
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

@Parcelize
data class OrderDetailAddress(
        val id: Int,
        val recipient: String,
        val mobile: String?,
        val country: String?,
        val province: String?,
        val city: String?,
        val county: String?,
        val detail: String?
) : Parcelable


@Parcelize
data class PayDialogData(
        var orderId: Int,
        var orderNum: String,
        var orderTime: String,
        var price: Float
) : Parcelable