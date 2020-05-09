package com.shangyi.kt.ui.mine.bean

/**
 * Date:2020/5/7
 * author:lwb
 * Desc:
 */
data class YhqListBean(
        val `data`: List<YhqItemBean>,
        val number: List<Number>
)

data class Number(
        val id: Int,
        val name: String,
        val number: Int
)

data class YhqItemBean(
        val id: Int,//优惠券id
        val receive_id: Int,//删除用的ID
        val price: Float?,//优惠券价格
        val name: String,//仅限购买商品使用
        val start_time: String, //使用开始时间
        val end_time: String, //使用结束时间
        val sign: String, //说明
        val type: Int, //优惠券类型
        val shop: Shop //店铺信息
)