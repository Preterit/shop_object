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
}