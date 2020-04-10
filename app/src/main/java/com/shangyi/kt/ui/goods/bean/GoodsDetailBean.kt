package com.shangyi.kt.ui.goods.bean

/**
 * Date:2020/4/9
 * author:lwb
 * Desc:
 */
class GoodsDetailBean {
}

data class GoodDetailTopBarBean(
        var imageRes: Int? = 0,
        var imageUrl: String? = "",
        var title: String? = "",
        var viewType: Int
)

data class GoodsDetailTjProduce(
        val str: String
)

