package com.shangyi.kt.fragment.home.model

/**
 * Date:2020/5/7
 * author:lwb
 * Desc:
 */
data class HomeDataBean(
        val count: Int,
        val dealer: Dealer,
        val discountList: List<DisCountListBean>?,
        val goods_one_img: GoodsOneImg,
        val goods_unit: GoodsUnit,
        val id: Int,
        val intro: String,
        val msales: Int,
        val name: String,
        val price: Float,
        val sale_count: Int,
        val sale_price: Float,
        val shop_id: Int,
        val unit_id: Int,
        val weight: Float
)

data class DisCountListBean(
        val id: Int,
        val type: Int,
        val shop_id: Any?,
        val price: Float?,
        val full_price: Float?,
        val start_time: String?,
        val end_time: String?
)

data class Dealer(
        val cash_back: Float,
        val dealer: Float,
        val gid: Int
)

data class GoodsOneImg(
        val good_id: Int,
        val url: String
)

data class GoodsUnit(
        val id: Int,
        val name: String
)


data class HomeBanner(
        val activity_id: Int,
        val create_time: String,
        val good_id: Int,
        val id: Int,
        val img_url: String,
        val name: String,
        val page_url: String,
        val update_time: String
)