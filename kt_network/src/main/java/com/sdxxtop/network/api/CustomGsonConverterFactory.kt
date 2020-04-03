package com.sdxxtop.network.api

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonToken
import com.sdxxtop.network.helper.data.BaseResponse
import com.sdxxtop.network.helper.data.BaseStringResponse
import com.sdxxtop.network.helper.exception.ApiException
import com.sdxxtop.network.utils.AESUtils
import com.sdxxtop.network.utils.SpUtil
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.lang.reflect.Type
import java.nio.charset.Charset
import kotlin.text.Charsets.UTF_8

/**
 * Date:2020/4/1
 * author:lwb
 * Desc:
 */
class CustomGsonConverterFactory(private val gson: Gson) : Converter.Factory() {
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

    private val TAG = "CustomGson"
    private val AES_KEY = SpUtil.getString("api_key")

    @SuppressLint("LongLogTag")
    override fun convert(value: ResponseBody): T {
        var response = value.string()
        val baseResponse = gson.fromJson(response, BaseResponse::class.java)

        /** 先将code与msg解析出来，code非0的情况下直接抛ApiException异常，这样我们就将这种异常交给onFailure()处理了**/
        if (baseResponse.code=="0") {
            value.close()
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
            Log.e(TAG, result.toString())
            return result
        }
    }
}