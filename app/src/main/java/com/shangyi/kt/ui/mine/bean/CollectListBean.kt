package com.shangyi.kt.ui.mine.bean

/**
 * Date:2020/5/7
 * author:lwb
 * Desc:
 */
data class CollectListBean(
        val goods: ArrayList<Good>,
        val shop_avatar: String?,
        val shop_id: Int,
        var isSelect: Boolean = false, // 是否选中
        var isEdit: Boolean = false, // 是否 编辑
        val shop_name: String
)

data class Good(
        val id: Int,
        val is_show: Int,
        val name: String,
        val sale_price: Float?,
        val shop_id: Int,
        val cash_back: Float?,   // 购买返现
        val dealer: Float?,   // 返佣
        var isSelect: Boolean = false, // 是否选中
        var isEdit: Boolean = false, // 是否 编辑
        val url: String
)