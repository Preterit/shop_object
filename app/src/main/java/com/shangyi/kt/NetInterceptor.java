package com.shangyi.kt;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

/**
 * Date:2020/4/2
 * author:lwb
 * Desc:
 */
public class NetInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().build();
        String url = request.url().toString();
        String phpFunction = getPhpFunction(request, url);
        Response response = null;
        try {
            response = chain.proceed(request);
        } finally {
            Observable.timer(500, TimeUnit.MILLISECONDS)
                    .observeOn(Schedulers.newThread())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {});
        }

        return response;
    }

    //如何获得okhttp请求的请求体
    private String getPhpFunction(Request request, String url) {
        String function = "";
        if (url.equals("http://abc.com/apiv2/")) {//php接口地址
            try {
                Buffer sink = new Buffer();
                request.body().writeTo(sink);
                String json = sink.readString(Charset.defaultCharset());
                JSONObject jsonObject = new JSONObject(json);
                function = jsonObject.getString("f");
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return function;
    }
}
