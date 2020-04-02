package com.shangyi.business.net;

import android.text.TextUtils;

import com.shangyi.business.BuildConfig;
import com.shangyi.business.network.Constants;
import com.shangyi.business.network.SpUtil;

/**
 * data: 2020/3/26 16:12
 * auther: Dz
 * function:
 */
public class Constom {

    //默认域名 在获取不到动态域名的情况下。
    private static String BASRURL_DEFAULT = "http://shop.xueli001.cn/";
    //测试环境 域名
    private static String BASRURL_DEBUG = "http://shop.xueli001.cn/";
    //获取存在sp中的动态域名
    private static String BASEURL = SpUtil.getString(Constants.BASEURL);

    /**
     * 判断是debug还是release
     */
    public final static String GETNETWORK = netWrok();


    public static String netWrok() {
        if (BuildConfig.DEBUG) {
            return BASRURL_DEBUG;
        } else {
            return !TextUtils.isEmpty(BASEURL) ? BASEURL : BASRURL_DEFAULT;
        }
    }

    public static final String KEY = "35549002c129b36f587aa2d8911ddce1";
    public static final String LOGIN_URL = "";


    public static final String BSAE_URL = "http://39.106.97.65";// 正式
    //注册的接口
    public final static String REGISTER_URL = "movieApi/user/v1/registerUser";
    //获取验证码的接口
    public final static String CODE_URL = "api/login/send_code";
}
