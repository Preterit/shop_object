package com.shangyi.kt.fragment.categroy.bean

/**
 * Date:2020/4/8
 * author:lwb
 * Desc:
 */
data class CategroyLeftBean(
        val father_id: Int,
        val id: Int,
        val level: Int,
        val name: String
)

data class CategroyRightBean(
        val category_img: List<String?>?, // 轮播图
        val child_list: List<ChildBean>?,
        val father_id: Int,
        val id: Int,
        val is_parent: Int,
        val level: Int,
        val name: String
)

data class CategoryImg(
        val category_id: Int,
        val img: String
)

data class ChildBean(
        val child_list: List<ChildItemBean>?,
        val father_id: Int,
        val id: Int,
        val is_parent: Int,
        val level: Int,
        val name: String
)

data class ChildItemBean(
        val image: String?,
        val father_id: Int,
        val id: Int,
        val is_parent: Int,
        val level: Int,
        val name: String
)
