package com.shangyi.kt

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Date:2020/4/1
 * author:lwb
 * Desc:
 */
class CustomCallAdapter(val returnType: Type) : CallAdapter<String, CustomCall<String>> {

    override fun adapt(call: Call<String>): CustomCall<String> {
        return CustomCall<String>(call)
    }

    override fun responseType(): Type {
        return returnType
    }
}

class CustomCallAdapterFactory : CallAdapter.Factory() {
    companion object {
        fun creat(): CustomCallAdapterFactory {
            return CustomCallAdapterFactory()
        }
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        //获取原始类型
        val rawType = getRawType(returnType)

        //返回值必须是Custom并且带有泛型（参数类型），根据APIService接口中的方法返回值，确定returnType
        //如 CustomCall<String> getCategories();，那确定returnType就是CustomCall<String>
        return if (rawType == CustomCall::class.java && returnType is ParameterizedType) {
            val callReturnType = getParameterUpperBound(0, returnType as ParameterizedType)
            CustomCallAdapter(callReturnType)
        } else {
            null
        }
    }
}

class CustomCall<R>(val call: Call<R>) {
    @Throws(IOException::class)
    fun get(): R? {
        return call.execute().body()
    }
}