package com.shangyi.kt.ui.splash.model

import android.util.Log
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Constants
import com.shangyi.business.network.SpUtil
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


/**
 * Date:2020/4/1
 * author:lwb
 * Desc:
 */
class SplashModel : BaseViewModel() {
    /**
     * 动态获取域名
     */
    fun loadSettingInfo() {
        val url = "http://39.106.156.132/server"
        val okHttpClient = OkHttpClient().newBuilder().build()
        val request: Request = Request.Builder()
                .url(url)
                .get() //默认就是GET请求，可以不写
                .build()
        val newCall = okHttpClient.newCall(request)
        newCall.enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("SplashModel --1-- ", "onFailure: ")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                var responseBody = response.body()
                val baseUrl = responseBody?.string()?.replace("\n", "")
                SpUtil.putString(Constants.BASEURL, baseUrl)
                responseBody?.close()

                /**
                 * 获取配置信息
                 */
                getSetting()
            }
        })
    }

    fun getSetting() {
        loadOnUI({
            RetrofitClient.apiService.getSetting("")
        }, {
            mIsLoadingShow.value = false
            SpUtil.putString(Constants.API_KEY, it?.api_key)
        }, { code, msg, t ->
            UIUtils.showToast(msg)
        })
    }

}