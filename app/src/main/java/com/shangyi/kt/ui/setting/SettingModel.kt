package com.shangyi.kt.ui.setting

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.sdxxtop.network.utils.AESUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Constants
import com.shangyi.business.network.Params
import com.shangyi.business.network.SpUtil
import com.shangyi.business.utils.FormatGson
import com.shangyi.business.utils.LogUtils
import com.shangyi.kt.ui.userlogin.bean.GetCodeBean
import com.shangyi.kt.ui.userlogin.bean.LoginSuccess

/**
 * Date:2020/4/2
 * author:lwb
 * Desc:
 */
class SettingModel : BaseViewModel() {

    var loginSuccess = MutableLiveData<Boolean>(false)


    /**
     * 登陆
     * login_type  ==  1、账号密码  2、短信
     */
    fun quitLogin() {
        showLoadingDialog(true)
        loadOnUI({
            val params = Params()
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.getQuitLogin(params.aesData)
        }, {
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            mIsLoadingShow.value = false
            UIUtils.showToast(msg)
        })
    }


}
