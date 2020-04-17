package com.shangyi.kt.ui.goods.bean

/**
 * Date:2020/4/17
 * author:lwb
 * Desc:
 */
data class GoodsSpecBean(
    val keys: List<String>,
    val list: List<SkuBean?>?
)

data class SkuBean(
    val child: List<SkuBean?>?,
    val name: String,
    val price: String,
    val stock: Int,
    val id: Int
)
