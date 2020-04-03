package com.shangyi.business.http;


import com.sdxxtop.network.utils.AESUtils;
import com.shangyi.business.net.Constom;

import org.json.JSONException;
import org.json.JSONObject;

import sing.util.LogUtil;

public class HttpResponse {

    public static void e(String msg) {
        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize ) {// 长度小于等于限制直接打印
            LogUtil.e(msg);
        }else {
            while (msg.length() > segmentSize ) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize );
                msg = msg.replace(logContent, "");
                LogUtil.e(logContent);
            }
            LogUtil.e(msg);// 打印剩余日志
        }
    }

    private JSONObject json;

    public HttpResponse(String str) {
        e("服务器返回的原数据：" + str);
        try {
            json = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMsg() {
        if (json != null) {
            String msg = json.optString("msg");
            return msg;
        }
        return "";
    }

    public String getJavaMsg() {
        if (json != null) {
            String msg = json.optString("message");
            return msg;
        }
        return "";
    }

    public String getCode() {
        if (json != null) {
            String obj = json.optString("ret");
            return obj;
        }
        return "";
    }
    public String getJavaCode() {
        if (json != null) {
            String obj = json.optString("result");
            return obj;
        }
        return "";
    }

    public String getData() {
        if (json != null) {
            try {
                return AESUtils.decrypt(json.optString("data"), Constom.KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public JSONObject getJsonData() {
        if (json != null) {
            try {
                return new JSONObject(getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
