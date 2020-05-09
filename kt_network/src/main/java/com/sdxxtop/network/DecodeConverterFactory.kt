package com.sdxxtop.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonToken
import com.sdxxtop.network.helper.data.BaseResponse
import com.sdxxtop.network.utils.AESUtils
import com.sdxxtop.network.utils.LongMsgLog
import com.sdxxtop.network.utils.SpUtil
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.lang.reflect.Type
import java.nio.charset.Charset


/**
 * Date:2020/4/6
 * author:lwb
 * Desc:
 */
class DecodeConverterFactory constructor(private val gson: Gson) : Converter.Factory() {

    companion object {
        fun create(): DecodeConverterFactory {
            return create(Gson())
        }

        fun create(gson: Gson): DecodeConverterFactory {
            return DecodeConverterFactory(gson)
        }
    }

    override fun responseBodyConverter(
            type: Type?,
            annotations: Array<Annotation?>?,
            retrofit: Retrofit?
    ): Converter<ResponseBody, *>? {

        val adapter = gson.getAdapter(TypeToken.get(type))
        return GsonCusResponseBodyConverter(gson, adapter)
    }

//    override fun requestBodyConverter(
//            type: Type?,
//            parameterAnnotations: Array<Annotation?>?,
//            methodAnnotations: Array<Annotation?>?,
//            retrofit: Retrofit?
//    ): Converter<*, RequestBody> {
//        val adapter = gson.getAdapter(TypeToken.get(type))
//        return GsonCusRequestBodyConverter(gson, adapter)
//    }


}

internal class GsonCusResponseBodyConverter<T>(
        private val gson: Gson,
        private val adapter: TypeAdapter<T>
) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {

        return try {
//            val jsonReader = gson.newJsonReader(value.charStream())
//            val result = adapter.read(jsonReader)
//            Log.e(TAG, "${result.toString()}")
//            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
//                throw JsonIOException("JSON document was not fully consumed.")
//            }
//            result

            var resultStr = value.string()
            var baseResponse = gson.fromJson(resultStr, BaseResponse::class.java)
            if (baseResponse.code == "0" && baseResponse.data != null && SpUtil.getString("api_key").isNotEmpty()) {
                val decrypt = AESUtils.decrypt(baseResponse.data as String, SpUtil.getString("api_key"))
                LongMsgLog.printMsg(decrypt)
                val fromJson = gson.fromJson(decrypt, Any::class.java)
                val resultResponse = BaseResponse<Any>(fromJson, baseResponse.code, baseResponse.msg)
                resultStr = gson.toJson(resultResponse)
            }
            return adapter.fromJson(resultStr)
        } finally {
            value.close()
        }
    }

}

internal class GsonCusRequestBodyConverter<T>(
        private val gson: Gson,
        private val adapter: TypeAdapter<T>
) : Converter<T, RequestBody> {

    @Throws(IOException::class)
    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer: Writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
    }

    companion object {
        private val MEDIA_TYPE = MediaType.get("application/json; charset=UTF-8")
        private val UTF_8 = Charset.forName("UTF-8")
    }

}