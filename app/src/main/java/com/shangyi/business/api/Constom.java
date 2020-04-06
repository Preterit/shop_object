package com.shangyi.business.api;

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
    private static String BASRURL_DEBUG = "http://vp7kh2.natappfree.cc/";
    //获取存在sp中的动态域名
    private static String BASEURL = SpUtil.getString(Constants.BASEURL);
    //获取存在sp中的加密key
    public static String API_KEY = !TextUtils.isEmpty(SpUtil.getString(Constants.API_KEY)) ? SpUtil.getString(Constants.API_KEY) : "1234567890123456";


    /**
     * 判断是debug还是release
     */
    public final static String GETNETWORK = netWrok();


    public static String netWrok() {
//        if (BuildConfig.DEBUG) {
//            return BASRURL_DEBUG;
//        } else {
        return !TextUtils.isEmpty(BASEURL) ? BASEURL : BASRURL_DEFAULT;
//        }
    }
}
