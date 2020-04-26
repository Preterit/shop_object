package com.shangyi.kt.ui.order.bean

/**
 * Date:2020/4/25
 * author:lwb
 * Desc:
 */
data class OrderListJsonBean(
        var gid: Int, // 商品ID
        var sid: Int, // 商品规格ID
        var number: Int, // 商品数量
        var pic: String = "", // 商品图片
        var remark: String = "" // 备注
)