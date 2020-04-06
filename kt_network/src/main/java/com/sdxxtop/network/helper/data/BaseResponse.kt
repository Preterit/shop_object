package com.sdxxtop.network.helper.data

/**
 * Email: zhousaito@163.com
 * Created by zhousaito 2019-07-30 09:29
 * Version: 1.0
 * Description:
 */
data class BaseResponse<T>(
        val `data`: T,
        val code: String,
        val msg: String

) {
    override fun toString(): String {
        return "BaseResponse(`data`=$`data`, code='$code', msg='$msg')"
    }
}
