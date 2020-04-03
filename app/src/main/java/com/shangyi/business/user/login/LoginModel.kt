package com.shangyi.business.user.login

import android.util.Log
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.sdxxtop.network.utils.AESUtils
import com.shangyi.business.net.Constom
import com.shangyi.business.network.Params
import com.shangyi.kt.FormatGson
import com.shangyi.kt.bean.GetCodeBean

/**
 * Date:2020/4/2
 * author:lwb
 * Desc:
 */
class LoginModel : BaseViewModel() {
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
        loadOnUI({
            val params = Params()
            params.put("login_name", phone)
            params.put("send_type", 1)
            RetrofitClient.apiService.getCode(params.aesData)
        }, {
            val forMatGson = FormatGson.instance.forMatGson(it, GetCodeBean::class.java)
            if (forMatGson != null) {
                Log.e("status -- ", "${forMatGson.status}")
            }
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