package com.shangyi.business.ui.splash.model

import android.util.Log
import com.sdxxtop.base.BaseViewModel
import com.shangyi.business.net.APIServer
import com.shangyi.business.net.Constom
import com.shangyi.kt.CustomCallAdapterFactory
import com.shangyi.kt.CustomGsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Date:2020/4/1
 * author:lwb
 * Desc:
 */
class SplashModel : BaseViewModel() {
    fun loadSettingInfo() {
//        showLoadingDialog(true)
//        loadOnUI({
//            val params = Params()
//            RetrofitClient.apiServicePort.postGetSetting()
//        }, {
//            mIsLoadingShow.value = false
//            Log.e("SplashModel -- ", "${it.toString()}")
//        }, { code, msg, t ->
//            UIUtils.showToast(msg)
//            mIsLoadingShow.value = false
//        })
        val retrofit = Retrofit.Builder()
                .baseUrl(Constom.GETNETWORK_PORT)
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(CustomCallAdapterFactory.creat())
                .build()
        val httpService: APIServer = retrofit.create(APIServer::class.java)
        val cookCall = httpService.postGetSetting()
        cookCall.enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("SplashModel --- ", "onFailure ---- ${t.message}")
            }

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.e("SplashModel --- ", "onResponse ---- ${response.body().toString()}")
            }

        })
    }

}