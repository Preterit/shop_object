package com.shangyi.kt.ui.goods.bean

/**
 * Date:2020/4/14
 * author:lwb
 * Desc:
 */

data class GoodsDetailBean(
        val address: Address?,
        val appraiseInfo: List<AppraiseInfoBean>,
        val comment_count: Int,
        val count: Int,
        val discountList: List<YouhuiquanBean?>?,
        val id: Int,
        val intro: String,  // 商品详情h5
        val msales: Int,   //月销量
        val name: String,
        val praise_count: Int,
        val price: Float,
        val reecommendGoods: List<ReecommendGood>?,
        val sale_count: Int,
        val sale_price: Float,
        val shop_id: Int,
        val unit: String,
        val goods_attribute: List<GoodsAttribute?>?,  // 商品属性
        val dealer: DealerBean?,  // 佣金 数据
        val spec: SpecBean?,  // 佣金 数据
        val goods_img: List<GoodsImgBean>?,  // 轮播图
        val shop_info: ShopInfoBean?,  // 商铺信息
        val goods_unit: GoodsUnitBean?,  // 轮播图
        val weight: Float
)

data class Address(
        val address: String?,
        val mobile: String,
        val name: String,
        val zip_code: Any
)

data class ReecommendGood(
        val comment_count: Any?,
        val count: Int,
        val id: Int,
        val intro: String?,
        val msales: Int,
        val name: String,
        val praise_count: Any,
        val price: Float,
        val sale_count: Float,
        val sale_price: Double,
        val shop_id: Int,
        val unit: String,
        val goods_one_img: GoodsOneImg?,
        val dealer: DealerBean?,
        val weight: Float
)

data class DealerBean(
        val gid: Int,
        val cash_back: Float,
        val dealer: Float
)

data class SpecBean(
        val product_id: Int,
        val value: String,
        val price: Float,
        val market_price: Float,
        val image: String
)

data class YouhuiquanBean(
        val id: Int,
        val type: Int,
        val shop_id: Int,
        val coupon_code: String,
        val price: Float,
        val full_price: Float
)

data class GoodsImgBean(
        val good_id: Int,
        val url: String
)

data class GoodsUnitBean(
        val id: Int,
        val name: String
)

data class GoodsAttribute(
        val name: String,
        val value: String
)

data class GoodsOneImg(
        val url: String
)

data class CommentImgBean(
        val url: String?,
        val comment_id: Int
)

data class UserBean(
        val avatar: String = "",
        val nickname: String
)

data class AppraiseInfoBean(
        val content: String, // 评论内容
        val name: String,
        val comment_img: List<CommentImgBean?>?,
        val user: UserBean?
)

data class ShopInfoBean(
        val id: Int,
        val name: String?,
        val shop_avatar: String?,
        val phone: String?,
        val address: String?,
        val shopRecommend: List<ReecommendGood>?
)