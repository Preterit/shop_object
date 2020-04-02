package com.shangyi.kt

import com.google.gson.Gson

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
    fun <T> forMatGson(json: String?, type: Class<T>): T? {
        var result: T? = null
        try {
            val toJson = gson.toJson(json)
            result = gson.fromJson(json, type) as T
        } catch (e: Exception) {
            result = null
        }
        return result
    }
}



