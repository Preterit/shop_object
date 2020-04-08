package com.shangyi.kt.ui.userlogin.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.FormatGson
import com.shangyi.kt.ui.userlogin.bean.GetCodeBean
import com.shangyi.kt.ui.userlogin.bean.LoginSuccess

/**
 * Date:2020/4/2
 * author:lwb
 * Desc:
 */
class LoginModel : BaseViewModel() {

    var loginSuccess = MutableLiveData<Boolean>(false)
    var registerSuccess = MutableLiveData<Boolean>(false)
    var commitInfoSuccess = MutableLiveData<Boolean>(false)
    var findPwdSuccess = MutableLiveData<Boolean>(false)


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
            } else {
                mIsLoadingShow.value = false
            }
        }, { code, msg, t ->
            mIsLoadingShow.value = false
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
        showLoadingDialog(true)
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
            mIsLoadingShow.value = false
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
            RetrofitClient.apiService.commitInfo(params.aesData)
        }, {
            commitInfoSuccess.value = it != null
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }


    fun loadWebViewData() {
        loadOnUI({
            var params = Params()
            params.put("login_name", "18614005205")
            params.put("password", "123456")
            params.put("code", "")
            params.put("login_type", 1)

            RetrofitClient.apiCusService.loginSSS(params.aesData)
        }, {
//            Log.e("LWB ---  data  -- ","${it.userInfo.login_name}")
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }


    /**
     * 找回密码
     */
    fun findPwd(phone: String,
                code: String,
                password: String = "",
                repeat_password: String = ""
    ) {
        showLoadingDialog(true)
        val params = Params()
        params.put("login_name", phone)
        params.put("code", code)
        params.put("password", password)
        params.put("repeat_password", repeat_password)
        loadOnUI({
            RetrofitClient.apiService.findpwd(params.aesData)
        }, {
            UIUtils.showToast("修改成功")
            mIsLoadingShow.value = false
            findPwdSuccess.value = true
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

}