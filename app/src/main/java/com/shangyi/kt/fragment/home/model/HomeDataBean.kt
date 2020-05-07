package com.shangyi.kt.fragment.home.model

/**
 * Date:2020/5/7
 * author:lwb
 * Desc:
 */
data class HomeDataBean(
        val count: Int,
        val dealer: Dealer,
        val discountList: List<Any>,
        val goods_one_img: GoodsOneImg,
        val goods_unit: GoodsUnit,
        val id: Int,
        val intro: String,
        val msales: Int,
        val name: String,
        val price: Int,
        val sale_count: Int,
        val sale_price: Float,
        val shop_id: Int,
        val unit_id: Int,
        val weight: Float
)

data class Dealer(
        val cash_back: Int,
        val dealer: Int,
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