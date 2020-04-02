package com.shangyi.business.api

import com.sdxxtop.network.helper.data.BaseResponse
import com.shangyi.kt.bean.GetSettingBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


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

    @FormUrlEncoded
    @POST("api/sys_config/getConfigInfo")
    suspend fun getSetting(@Field("data") data: String): BaseResponse<GetSettingBean?>

    @FormUrlEncoded
    @POST("api/sys_config/getConfigInfo")
    suspend fun getAESSetting(@Field("data") data: String): BaseResponse<GetSettingBean?>

}