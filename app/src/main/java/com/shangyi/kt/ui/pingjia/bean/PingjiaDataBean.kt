package com.shangyi.kt.ui.pingjia.bean

/**
 * Date:2020/4/21
 * author:lwb
 * Desc:
 */
data class PingjiaDataBean(
    val count: Count?,
    val list: List<ContentBean?>?
)

data class Count(
    val difreview: Int,
    val gid: Int,
    val hasimg: Int,
    val inreview: Int,
    val praise: Int,
    val total: Int
)

data class ContentBean(
    val child: List<Any>,
    val comment_img: List<CommentImg?>?,
    val content: String,
    val create_time: String?,
    val father_id: Int,
    val gid: Int,
    val goods_info: GoodsInfo,
    val id: Int,
    val oid: Int,
    val star_level: Int,
    val user: User?,
    val user_id: Int
)

data class CommentImg(
    val comment_id: Int,
    val url: String?
)

data class GoodsInfo(
    val id: Int,
    val name: String,
    val sku: Any
)

data class User(
    val avatar: Any,
    val id: Int,
    val nickname: String
)