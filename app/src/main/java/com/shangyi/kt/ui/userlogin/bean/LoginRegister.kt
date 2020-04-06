package com.shangyi.kt.ui.userlogin.bean

/**
 * Date:2020/4/2
 * author:lwb
 * Desc:
 */
data class GetCodeBean(
        val status: Int
)

data class LoginSuccess(val userInfo: UserInfoBean)

data class UserInfoBean(
        val id: Int,
        val login_name: String,
        val nickname: String,
        val mobile: String,
        val password: String,
        val avatar: String,
        val birthday: String,
        val sign: String,
        val gender: Int,
        val status: Int,
        val type: Int,
        val grade: Int,
        val father_id: Int,
        val create_time: String,
        val modify_time: String,
        val token: String


) {
    override fun toString(): String {
        return "UserInfoBean(id=$id, login_name='$login_name', nickname='$nickname', mobile='$mobile', password='$password', avatar='$avatar', birthday='$birthday', sign='$sign', gender=$gender, status=$status, type=$type, grade=$grade, father_id=$father_id, create_time='$create_time', modify_time='$modify_time', token='$token')"
    }
}


