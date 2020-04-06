package com.shangyi.kt.ui.userlogin.bean

/**
 * Date:2020/4/6
 * author:lwb
 * Desc:
 */
data class LLL(
    val code: String,
    val `data`: Data,
    val msg: String
)

data class Data(
    val userInfo: UserInfo
)

data class UserInfo(
    val gender: String,
    val grade: Double,
    val id: Double,
    val login_name: String,
    val nickname: String,
    val token: String,
    val type: Double
)