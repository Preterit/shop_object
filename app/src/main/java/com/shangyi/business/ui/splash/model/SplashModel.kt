package com.shangyi.business.ui.splash.model

import android.util.Log
import com.sdxxtop.base.BaseViewModel
import com.shangyi.kt.NetInterceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


/**
 * Date:2020/4/1
 * author:lwb
 * Desc:
 */
class SplashModel : BaseViewModel() {
    fun loadSettingInfo() {
        val url = "http://39.106.156.132/server"
        val okHttpClient = OkHttpClient().newBuilder().addInterceptor(NetInterceptor()).build()
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
                Log.e("SplashModel --2-- ", "onResponse: ${responseBody?.string()}")
                responseBody?.close()
            }

        })
    }

}