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

data class GoodsListBean(
        val comment_count: Double,
        val count: Int,
        val goods_img: List<GoodsImg?>,
        val id: Int,
        val intro: Any,
        val name: String,
        val praise_count: Double,
        val price: Double,
        val sale_count: Int,
        val sale_price: Double,
        val shop_id: Int,
        val unit: String,
        val weight: Int
)

data class GoodsImg(
        val good_id: Int,
        val url: String
)

