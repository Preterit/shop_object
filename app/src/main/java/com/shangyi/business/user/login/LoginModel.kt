package com.shangyi.business.user.login

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.kt.FormatGson
import com.shangyi.business.user.login.bean.GetCodeBean
import com.shangyi.business.user.login.bean.LoginSuccess

/**
 * Date:2020/4/2
 * author:lwb
 * Desc:
 */
class LoginModel : BaseViewModel() {

    var loginSuccess = MutableLiveData<Boolean>(false)
    var registerSuccess = MutableLiveData<Boolean>(false)
    var commitInfoSuccess = MutableLiveData<Boolean>(false)


    /**
     * 登陆
     * login_type  ==  1、账号密码  2、短信
     */
    fun login(phone: String,
              pwd: String,
              code: String,
              login_type: Int
    ) {
        showLoadingDialog(true)
        loadOnUI({
            val params = Params()
            params.put("login_name", phone)
            params.put("password", pwd)
            params.put("code", code)
            params.put("login_type", login_type)
            RetrofitClient.apiService.login(params.aesData)
        }, {
            val bean = FormatGson.instance.forMatGson(it, LoginSuccess::class.java)
            if (bean?.userInfo != null) {
                loginSuccess.value = true
            }
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
        })
    }

    /**
     * 获取验证码
     *  type == 1、注册 2、登录 3、找回密码
     */
    fun getCode(phone: String, type: Int) {
        loadOnUI({
            val params = Params()
            params.put("login_name", phone)
            params.put("send_type", type)
            RetrofitClient.apiService.getCode(params.aesData)
        }, {
            val forMatGson = FormatGson.instance.forMatGson(it, GetCodeBean::class.java)
            if (forMatGson != null) {
                UIUtils.showToast("获取验证码成功")
            }
        }, { code, msg, t ->
            UIUtils.showToast(msg)
        })
    }

    /**
     * 注册
     */
    fun register(phone: String,
                 code: String,
                 password: String = "",
                 repeat_password: String = ""
    ) {
        val params = Params()
        params.put("login_name", phone)
        params.put("code", code)
        params.put("password", password)
        params.put("repeat_password", repeat_password)
        loadOnUI({
            RetrofitClient.apiService.register(params.aesData)
        }, {
            val forMatGson = FormatGson.instance.forMatGson(it, Any::class.java)

            registerSuccess.value = forMatGson != null

            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
        })
    }

    /**
     * 提交个人信息
     */
    fun commitInfo(registerphone: String,
                   nickname: String,
                   gender: Int,
                   birthday: String,
                   sign: String) {
        showLoadingDialog(true)
        loadOnUI({
            val params = Params()
            params.put("login_name", registerphone)
            params.put("nickname", nickname)
            params.put("gender", gender)
            params.put("birthday", birthday)
            params.put("sign", sign)
            RetrofitClient.apiService.register(params.aesData)
        }, {
            val forMatGson = FormatGson.instance.forMatGson(it, Any::class.java)
            commitInfoSuccess.value = it == null
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}