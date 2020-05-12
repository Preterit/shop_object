package com.shangyi.business.api

import android.text.TextUtils
import com.sdxxtop.network.api.BaseRetrofitClient
import com.shangyi.business.network.Constants
import com.shangyi.business.network.SpUtil
import okhttp3.OkHttpClient

/**
 * Email: zhousaito@163.com
 * Created by zhousaito 2019-07-29 20:18
 * Version: 1.0
 * Description:
 */
object RetrofitClient : BaseRetrofitClient() {
    val apiService by lazy {
        getService(ApiService::class.java, getBaseUrl())
    }
    val apiCusService by lazy {
        getCusService(ApiService::class.java, getBaseUrl())
    }

    override fun isDebug(): Boolean {
        return true
    }

    override fun handleBuilder(builder: OkHttpClient.Builder) {

    }

    fun getBaseUrl() = if (!TextUtils.isEmpty(SpUtil.getString(Constants.BASEURL))) SpUtil.getString(Constants.BASEURL)
    else "http://shop.xueli001.cn/"

}