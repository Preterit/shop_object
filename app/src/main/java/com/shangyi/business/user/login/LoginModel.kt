package com.shangyi.business.user.login

import android.util.Log
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.http.AESUtils
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
        val params = Params()
        params.put("login_name", phone)
        params.put("send_type", 1)
        loadOnUI({
            RetrofitClient.apiService.getCode(params.aesData)
        }, {
            if (it != null) {
                val decrypt = AESUtils.decrypt(it, Constom.API_KEY)
                Log.e("解密的数据  key --- ", "${Constom.API_KEY} 解密的数据 --- ${decrypt}")

                val forMatGson = FormatGson.instance.forMatGson(decrypt, GetCodeBean::class.java)
                if (forMatGson!=null){
                    Log.e("status -- ","${forMatGson.status}")
                }
            }
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