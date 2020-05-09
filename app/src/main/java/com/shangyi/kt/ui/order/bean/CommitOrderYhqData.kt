package com.shangyi.kt.ui.order.bean

/**
 * Date:2020/5/9
 * author:lwb
 * Desc:
 */
data class CommitOrderYhqData(
        val can_use: Int,
        val full_price: Float,
        val goods_id: Int,
        val id: Int,
        val merchant_id: String,  // 系统优惠券  只能有一张
        val price: Float,
        val shop_id: Any,
        var isSelect: Boolean = false,
        val type: Int
)