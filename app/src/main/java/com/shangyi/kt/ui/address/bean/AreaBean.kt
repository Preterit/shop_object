package com.shangyi.kt.ui.address.bean

/**
 * Date:2020/4/20
 * author:lwb
 * Desc:
 */
data class AreaBean(
        val father: FatherData?,
        val list: List<AreaItemBean>
)

data class AreaItemBean(
        val id: Int,
        val type: Int,
        val name: String
)

data class FatherData(
        val id: Int,
        val type: Int,
        val name: String?,
        val province_id: Int
)