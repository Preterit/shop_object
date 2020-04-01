package com.shangyi.kt

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonToken
import com.sdxxtop.network.helper.exception.ApiException
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.reflect.Type
import java.nio.charset.Charset
import kotlin.text.Charsets.UTF_8

/**
 * Date:2020/4/1
 * author:lwb
 * Desc:
 */
class CustomGsonConverterFactory(val gson: Gson) : Converter.Factory() {
    companion object {
        fun create(): CustomGsonConverterFactory {
            return create(Gson())
        }

        private fun create(gson: Gson?): CustomGsonConverterFactory {
            if (gson == null) throw NullPointerException("gson == null")
            return CustomGsonConverterFactory(gson)
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        Log.e("CustomGsonConve -- ", "responseBodyConverter -- ${type!!.typeName}")
        return CustomGsonResponseBodyConverter(gson, gson.getAdapter(TypeToken.get(type)))
    }

    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<out Annotation>?, methodAnnotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        return CustomGsonRequestBodyConverter(gson, gson.getAdapter(TypeToken.get(type)))
    }
}

class CustomGsonRequestBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<T, RequestBody> {
    private val MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8")
    private val UTF_8 = Charset.forName("UTF-8")

    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
    }
}

class CustomGsonResponseBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T {
        val response = value.string()
        Log.e("CustomGsonConve -- ", "${value.toString()}")
        val httpStatus = gson.fromJson(response, HttpStatus::class.java)
        /** 先将code与msg解析出来，code非0的情况下直接抛ApiException异常，这样我们就将这种异常交给onFailure()处理了**/
        if (!httpStatus.isSuccess()) {
            value.close()
            throw ApiException(httpStatus.code, httpStatus.msg)
        }
        val contentType = value.contentType()
        val charset = contentType?.charset(UTF_8) ?: UTF_8
        val inputStream = ByteArrayInputStream(response.toByteArray())
        val reader = InputStreamReader(inputStream, charset!!)
        val jsonReader = gson.newJsonReader(reader)

        value.use { _ ->
            val result = adapter.read(jsonReader)
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw JsonIOException("JSON document was not fully consumed.")
            }
            return result
        }
    }
}

open class HttpStatus {
    var msg: String? = null
    var code: Int = -1
    fun isSuccess(): Boolean {
        return code == 0
    }
}