package com.shangyi.business.utils;

import com.shangyi.business.network.Constants;
import com.shangyi.business.network.NetWorkSession;
import com.shangyi.business.utils.interceptor.NetInterceptor;
import com.shangyi.business.utils.interceptor.NoNetInterceptor;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
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

    private HashMap<String, String> mMap = new HashMap<>();

    /**
     * 单例模式
     *
     * @return
     */
    public static OkHttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtils.class) {
                instance = new OkHttpUtils();
            }
        }
        return instance;
    }

    private OkHttpUtils() {
        mMap.put("ak", "0110010010000");
        mMap.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        initHttpClient();
    }

    /**
     * 初始化okhttpClient
     */
    private void initHttpClient() {
        if (mOkHttpClient != null) {
            return;
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (NetWorkSession.DEBUG()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }

        builder.addInterceptor(new NoNetInterceptor())
                .addNetworkInterceptor(new NetInterceptor());
        File file = new File(Constants.PATH_CACHE);
        //最多缓存100m
        builder.cache(new Cache(file, 100 * 1024 * 1024));

        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);

        builder.retryOnConnectionFailure(true);

        mOkHttpClient = builder.build();
    }

    OkHttpClient.Builder generateDefaultBuilder() {

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
