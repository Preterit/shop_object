package com.shangyi.business.api

import com.sdxxtop.network.helper.data.BaseResponse
import com.shangyi.kt.ui.splash.bean.GetSettingBean
import com.shangyi.kt.ui.userlogin.bean.LoginSuccess
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


    @FormUrlEncoded
    @POST("api/login/login")
    suspend fun login(@Field("data") data: String): BaseResponse<String?>

    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST("api/login/send_code")
    suspend fun getCode(@Field("data") data: String): BaseResponse<String?>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("api/login/register")
    suspend fun register(@Field("data") data: String): BaseResponse<String?>

    /**
     * 完善个人信息
     */
    @FormUrlEncoded
    @POST("api/login/improveInfo")
    suspend fun commitInfo(@Field("data") data: String): BaseResponse<String?>


    @FormUrlEncoded
    @POST("api/login/login")
    suspend fun loginSSS(@Field("data") data: String): BaseResponse<LoginSuccess>

    /**
     * 找回密码
     */
    @FormUrlEncoded
    @POST("api/login/findPwd")
    suspend fun findpwd(@Field( "data") data: String): BaseResponse<String?>
}