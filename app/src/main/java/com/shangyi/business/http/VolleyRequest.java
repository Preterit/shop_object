package com.shangyi.business.http;


import com.alibaba.fastjson.JSONObject;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.shangyi.business.base.MyApplication;
import com.shangyi.business.net.Constom;

import sing.util.LogUtil;

public class VolleyRequest {

    public static void RequestPost(String url, String tag, JSONObject jsonObject, VolleyInterface vif) {
        String AESContent = null;

        try {
            AESContent = AESUtils.encrypt(jsonObject.toString(), Constom.KEY);
            AESContent = AESContent.replace("\n", "");

            LogUtil.e("------加密前的数据为------");
            LogUtil.e(jsonObject.toString());
            LogUtil.e("------加密后的数据为------");
            LogUtil.e(AESContent);
            LogUtil.e("------解密后的数据为------");
            LogUtil.e(AESUtils.decrypt(AESContent, Constom.KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }


       /* JsonObjectRequest objectRequest = new JsonObjectRequest(Method.POST, url, AESContent, vif.loadingListener(), vif.errorListener());
        objectRequest.setTag(tag);
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 0, 1.0f));
        MyApplication.getInstance().getHttpQueue().add(objectRequest);
      */  //MyApplication.getInstance().getHttpQueue().start();
    }
}
