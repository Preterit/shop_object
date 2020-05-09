package com.sdxxtop.network.utils;

import android.text.TextUtils;
import android.util.Log;

import com.sdxxtop.network.NetworkSession;

/**
 * Date:2020/5/9
 * author:lwb
 * Desc:
 */
public class LongMsgLog {

    private static final String TAG = "LongMsgLog";

    /**
     * 打印长数据
     */
    public static void printMsg(String decrypt) {
        if (TextUtils.isEmpty(decrypt)) {
            return;
        }
        try {
            e(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(String msg) {  //信息太长,分段打印
        if (!NetworkSession.getIsDebug()) {
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
}
