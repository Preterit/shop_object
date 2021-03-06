package com.shangyi.kt.fragment.mine.bean

/**
 * Date:2020/4/28
 * author:lwb
 * Desc:
 */
data class MineBean(
        val amount: AmountBean,  //提现金额
        val avatar: String?,  // 头像
        val grade: Int,   //等级 1、一级经销商 2、二级经销商 3、普通经销商',
        val id: Int,
        val nickname: String,  // 昵称
        val orders: List<Order>,
        val recommend: List<MineTjItem>
)

data class Order(
        val order_status: String,
        val status_num: Int
)

data class MineTjItem(
        val id: Int,
        val name: String
)

data class AmountBean(val amount: String)