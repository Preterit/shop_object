package com.shangyi.business;

import androidx.multidex.MultiDex;

import com.sdxxtop.base.BaseApplication;
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
        instance = this;
        MultiDex.install(this);
        NetWorkSession.init(this, BuildConfig.DEBUG);


        ToastUtil.init(this);
        LogUtil.init(true, "111");
        SharedPreferencesUtil.init(this, "");

    }

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }


    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public int versionCode() {
        return BuildConfig.VERSION_CODE;
    }
}
