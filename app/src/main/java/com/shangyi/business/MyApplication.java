package com.shangyi.business;

import androidx.multidex.MultiDex;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.sdxxtop.base.BaseApplication;
import com.sdxxtop.base.BuildConfig;
import com.shangyi.business.network.NetWorkSession;

import sing.util.LogUtil;
import sing.util.SharedPreferencesUtil;
import sing.util.ToastUtil;

/**
 * data: 2020/3/17 17:37
 * auther: Dz
 * function:
 */
public class MyApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        // 在加载图片之前，你必须初始化Fresco类
        Fresco.initialize(this);
        NetWorkSession.init(this, BuildConfig.DEBUG);

        instance = this;
        mQueue = Volley.newRequestQueue(this);

        ToastUtil.init(this);
        LogUtil.init(true, "111");
        SharedPreferencesUtil.init(this, "");

    }


    public RequestQueue mQueue;
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    public RequestQueue getHttpQueue() {
        return mQueue;
    }


    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}