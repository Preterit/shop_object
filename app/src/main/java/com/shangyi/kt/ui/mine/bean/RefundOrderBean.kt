package com.shangyi.kt.ui.mine.bean

import com.shangyi.kt.ui.order.bean.OrderDetailGood

/**
 * Date:2020/5/14
 * author:lwb
 * Desc:
 */
data class RefundOrderBean(
        val create_time: String,
        val explain: String,
        val gid: Int,
        val goods: List<OrderDetailGood>?,
        val id: Int,
        val oid: Int,
        val reason: Any,
        val refund_price: String,
        val refund_sn: String,
        val remark: String,
        val status: Int,
        val update_time: String
)
