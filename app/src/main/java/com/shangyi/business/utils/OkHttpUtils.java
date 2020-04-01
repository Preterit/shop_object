package com.shangyi.business.utils;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * data: 2020/3/18 10:25
 * auther: Dz
 * function:
 */
public class OkHttpUtils {
    private static OkHttpUtils instance;

    protected OkHttpClient mOkHttpClient;

    private HashMap<String,String> mMap = new HashMap<>();

    /**
     * 单例模式
     * @return
     */
    public static OkHttpUtils getInstance(){
        if (instance == null){
            synchronized (OkHttpUtils.class){
                instance = new OkHttpUtils();
            }
        }
        return instance;
    }
    private OkHttpUtils(){
        mMap.put("ak","0110010010000");
        mMap.put("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

    }

    OkHttpClient.Builder generateDefaultBuilder(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //添加日志拦截器
                .addInterceptor(new LogginInterceptor(mMap))
                .addInterceptor(new HttpLoggingInterceptor().
                        setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder;
    }

    void updateUserId(String userId, String sessionId) {
        mMap.put("userId", userId);
        mMap.put("sessionId", sessionId);
    }

}
