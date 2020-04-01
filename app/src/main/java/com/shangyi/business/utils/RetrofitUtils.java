package com.shangyi.business.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shangyi.business.net.Constom;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * date:2019/1/2
 * author:赵豪轩(xuan)
 * function:
 */
public class RetrofitUtils {

    private Retrofit mRetrofit;
    private static RetrofitUtils instance;
    private Gson mGson;

    private RetrofitUtils(){
        mGson = new GsonBuilder()
                .create();
        mRetrofit = new Retrofit.Builder().baseUrl(Constom.GETNETWORK)
                //RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //gson
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .client(OkHttpUtils.getInstance().mOkHttpClient)
                .build();
    }

    //单例模式
    public static RetrofitUtils getInstance(){
        if (instance == null){
            synchronized (RetrofitUtils.class){
                if (instance == null){
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    public <T> T getBean(String json,Class<T> clzz){
        return mGson.fromJson(json,clzz);
    }

    public String getJson(Object obj){
        return mGson.toJson(obj);
    }

    public <T> T getService(Class<T> clzz){
        return mRetrofit.create(clzz);
    }

    public void updateUserId(String userId, String sessionId) {
        OkHttpUtils.getInstance().updateUserId(userId, sessionId);
    }
}
