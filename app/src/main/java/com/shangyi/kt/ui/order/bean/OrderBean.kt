package com.shangyi.kt.ui.order.bean

import android.util.Log

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

data class YfDataBean(
        val delivery: Float
)

data class OrderPayBefore(
        val order_id: String,
        val order_num: String,
        val pay_amount: Float
)

data class OrderInfo(
        val info: String
)

data class WxOrderInfo(
        val info: WxRequest
)

data class WxRequest(
        val appid: String,
        val noncestr: String,
        val `package`: String,
        val partnerid: String,
        val prepayid: String,
        val sign: String,
        val timestamp: Long
) {
    override fun toString(): String {
        return "WxRequest(appid='$appid', nonce_str='$noncestr', `package`='$`package`', partnerid='$partnerid', prepayid='$prepayid', sign='$sign', timestamp='$timestamp')"
    }
}