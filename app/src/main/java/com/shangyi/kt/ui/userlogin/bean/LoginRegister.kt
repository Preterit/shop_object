package com.shangyi.kt.ui.userlogin.bean

/**
 * Date:2020/4/2
 * author:lwb
 * Desc:
 */
data class GetCodeBean(
        val status: Int
)

data class LoginSuccess(val userInfo: UserInfo)

data class UserInfo(
        val avatar: Any,
        val birthday: Any,
        val gender: String,
        val grade: Int,
        val id: Int,
        val login_name: String,
        val mobile: Any,
        val nickname: String,
        val sign: Any,
        val token: String,
        val type: Int
)



