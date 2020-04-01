package com.shangyi.business.api

import com.sdxxtop.network.helper.data.BaseResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET


/**
 * Email: zhousaito@163.com
 * Created by zhousaito 2019-07-29 20:21
 * Version: 1.0
 * Description:
 */

interface ApiService {

    companion object {
//        const val BASE_URL = "http://yinanapi.sdxxtop.com/api/"
//        const val BASE_URL = "http://envir.test.sdxxtop.com/api/"
    }

    @GET("server")
    suspend fun postGetSetting(): String
}