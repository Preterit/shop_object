package com.shangyi.business.user.login

import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params

/**
 * Date:2020/4/2
 * author:lwb
 * Desc:
 */
class LoginModel:BaseViewModel() {
    fun login(phone: String, pwd: String) {
        val params = Params()
        params.put("login_name", phone)
        params.put("password", pwd)
        loadOnUI({
            RetrofitClient.apiService.login(params.aesData)
        }, {

            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
        })
    }

    /**
     * 获取验证码
     */
    fun getCode(phone: String) {
        val params = Params()
        params.put("login_name", phone)
        params.put("send_type", 1)
        loadOnUI({
            RetrofitClient.apiService.getCode(params.aesData)
        }, {

            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
        })
    }

    /**
     * 注册
     */
    fun register(phone: String, code: String) {
        val params = Params()
        params.put("login_name", phone)
        params.put("code", code)
        loadOnUI({
            RetrofitClient.apiService.register(params.aesData)
        }, {

            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
        })
    }
}