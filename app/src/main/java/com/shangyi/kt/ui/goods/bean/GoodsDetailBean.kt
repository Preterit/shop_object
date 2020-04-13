package com.shangyi.kt.ui.goods.bean

/**
 * Date:2020/4/9
 * author:lwb
 * Desc:
 */

/**
 *  商品信息
 */
data class GoodsDetailBean(
        val appraiseInfo: List<Any>,
        val comment_count: Any,
        val count: Int,
        val discountList: List<Any>,
        val id: Int,
        val intro: Any,
        val name: String,
        val praise_count: Any,
        val price: Double,  //商品价格
        val reecommendGoods: List<ReecommendGood>,
        val sale_count: Int,  // 已售数量
        val sale_price: Double,  //商品销售价
        val msales: Int,       //月销量
        val shop_id: Int,
        val unit: String,
        val weight: Int
)

/**
 * 推荐商品
 */
data class ReecommendGood(
        val comment_count: Any,
        val count: Int,
        val id: Int,
        val intro: Any,
        val name: String,
        val praise_count: Any,
        val price: Int,
        val sale_count: Int,
        val sale_price: Double,
        val shop_id: Int,
        val unit: String,
        val weight: Int
)

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

