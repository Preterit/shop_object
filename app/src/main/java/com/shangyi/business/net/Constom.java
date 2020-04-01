package com.shangyi.business.net;

/**
 * data: 2020/3/26 16:12
 * auther: Dz
 * function:
 */
public class Constom {


    private final static boolean flag = false;

    /**
     * 通过flag返回内网/外网测试环境
     */
    public final static String GETNETWORK = netWrok();

    public static String netWrok() {
        if (flag) {
            //内网环境
            return "http://xxx.xx.x";
        } else {
            //外网环境
            return "http://xxx.xxx.com";
        }
    }

    public static final String KEY = "35549002c129b36f587aa2d8911ddce1";
    public static final String LOGIN_URL = "";


    public static final String BSAE_URL = "http://39.106.97.65";// 正式
    //注册的接口
    public final static String REGISTER_URL = "movieApi/user/v1/registerUser";
}
