package com.shangyi.kt

import android.util.Log
import com.google.gson.Gson
import com.sdxxtop.network.utils.AESUtils
import com.shangyi.business.net.Constom

/**
 * Date:2020/4/2
 * author:lwb
 * Desc:
 */

class FormatGson private constructor() {

    companion object {
        val instance: FormatGson by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            FormatGson()
        }
    }

    val gson = Gson()

    fun <T> forMatGson(aesStr: String?, type: Class<T>): T? {
        var result: T? = null
        if (aesStr.isNullOrEmpty()) {
            return null
        }
        val json = AESUtils.decrypt(aesStr, Constom.API_KEY)
        Log.e("AES decode --- ", json)
        try {
            val toJson = gson.toJson(json)
            result = gson.fromJson(json, type) as T
        } catch (e: Exception) {
            result = null
        }
        return result
    }

}



