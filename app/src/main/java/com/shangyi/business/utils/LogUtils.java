package com.shangyi.business.utils;


import android.util.Log;

import com.sdxxtop.network.utils.AESUtils;
import com.shangyi.business.BuildConfig;
import com.shangyi.business.network.Constants;
import com.shangyi.business.network.Params;
import com.shangyi.business.network.SpUtil;


/**
 * Date:2020/5/4
 * author:lwb
 * Desc:
 */
public class LogUtils {

    private static final String TAG = "LogUtils";

    public static void e(String msg) {  //信息太长,分段打印
        if (!BuildConfig.DEBUG) {
            return;
        }

        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - TAG.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.e(TAG, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.e(TAG, msg);
    }

    /**
     * 打印提交的的参数
     * @param params
     */
    public static void deCodeParams(Params params) {
        if (SpUtil.getString(Constants.API_KEY).isEmpty() || params.getAESData().isEmpty()) {
            return;
        }
        try {
            String decrypt = AESUtils.decrypt(params.getAESData(), SpUtil.getString(Constants.API_KEY));
            e(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印提交的的参数
     *
     * @param params
     */
    public static void deCodeResult(Params params) {
        if (SpUtil.getString(Constants.API_KEY).isEmpty() || params.getAESData().isEmpty()) {
            return;
        }
        try {
            String decrypt = AESUtils.decrypt(params.getAESData(), SpUtil.getString(Constants.API_KEY));
            e(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
