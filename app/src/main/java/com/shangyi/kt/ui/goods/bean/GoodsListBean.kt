package com.shangyi.kt.ui.goods.bean

/**
 * Date:2020/4/9
 * author:lwb
 * Desc:
 */


data class GoodDetailTopBarBean(
        var imageRes: Int? = 0,
        var imageUrl: String? = "",
        var title: String? = "",
        var viewType: Int
)

data class GoodsListBean(
        val comment_count: Int = 0,  // 总评论数
        val count: Int,
        val goods_img: List<GoodsImg?>,
        val discountList: List<YouhuiquanBean?>,
        val id: Int,
        val intro: Any,
        val name: String,
        val praise_count: Int = 0,   // 好评数
        val price: Double,
        val sale_count: Int,
        val sale_price: Double,
        val shop_id: Int,
        val unit: String,
        val dealer: DealerBean?,
        val weight: Float
)

data class GoodsImg(
        val good_id: Int,
        val url: String
)

