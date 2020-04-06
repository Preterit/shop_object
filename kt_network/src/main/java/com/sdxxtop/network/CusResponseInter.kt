package com.sdxxtop.network

import android.util.Log
import com.google.gson.Gson
import com.sdxxtop.network.helper.data.BaseResponse
import com.sdxxtop.network.utils.AESUtils
import com.sdxxtop.network.utils.SpUtil
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Date:2020/4/6
 * author:lwb
 * Desc:
 */
class CusResponseInter : Interceptor {
    val gson = Gson()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        try {
            val json = response.body()?.string()
            val aESResponse = gson.fromJson(json, AESResponse::class.java)
            if (aESResponse.code == "0" && aESResponse.data != null && getAESKey().isNotEmpty()) {
                val decrypt = AESUtils.decrypt(aESResponse.data, getAESKey())
                Log.e("lwb --- intercept --- ", "AES decrypt data is $decrypt")
                val fromJson = gson.fromJson<Any>(decrypt, Data::class.java)
                val responseGson = BaseResponse(fromJson,aESResponse.code,aESResponse.msg!!)

                val resultResponse = gson.toJson(responseGson)
                val myBody = ResponseBody.create(MediaType.get("text/plain"), resultResponse)
                response = response.newBuilder().body(myBody).build()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("lwb --- okhttp --- ", "intercept error -- ${e.message}")
        }
        return response
    }

    fun getAESKey() = SpUtil.getString("api_key")
}

data class AESResponse(
        val code: String?,
        val msg: String?,
        var data: String?
)

data class Data(
        val userInfo: UserInfo
)

data class UserInfo(
        val gender: String,
        val grade: Double,
        val id: Double,
        val login_name: String,
        val nickname: String,
        val token: String,
        val type: Double
)