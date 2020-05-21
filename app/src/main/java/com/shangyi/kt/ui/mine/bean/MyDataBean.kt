package com.shangyi.kt.ui.mine.bean

/**
 * Date:2020/5/21
 * author:lwb
 * Desc:
 */
data class MyDataBean(
        val count_total: Float,
        val list: List<ItemBean>,
        val month_total: Float
)

data class ItemBean(
        val list: List<ChildItemBean>,
        val time: String,
        val total: Float
)

data class ChildItemBean(
        val amount: Float,
        val create_time: String,
        val order_num: String
)