package com.shangyi.business.utils;


import android.app.Activity;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.shangyi.business.base.LoadingDialog;
import com.shangyi.business.bean.ParmsBean;
import com.shangyi.business.http.HttpResponse;
import com.shangyi.business.http.RequestCallBack;
import com.shangyi.business.http.VolleyInterface;
import com.shangyi.business.http.VolleyRequest;


import sing.MaterialRefreshLayout;
import sing.util.LogUtil;
import sing.util.StringUtil;
import sing.util.ToastUtil;

public final class RequestUtils {

    private String tag = "request";
    private String url;
    private RequestCallBack callBack;
    private LoadingDialog dialog;
    private MaterialRefreshLayout mRrefreshLayout;
    private Activity context;

    public RequestUtils(Activity context) {
        if (context != null) {
            dialog = new LoadingDialog(context);
        }
    }

    JSONObject json;

    public RequestUtils(Context context, MaterialRefreshLayout mRrefreshLayout) {
        if (context != null) {
            dialog = new LoadingDialog(context);
        }
        this.mRrefreshLayout = mRrefreshLayout;

        json = new JSONObject();
    }

    public RequestUtils tag(String tag) {
        this.tag = tag;
        return this;
    }

    public RequestUtils parms(ParmsBean temp) {
        try {
            this.json.put(temp.KEY, temp.VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public RequestUtils url(String url) {
        this.url = url;
        LogUtil.e("url = " + url);
        return this;
    }

    // 这个必须调用,flag为true代表是JAVA接口，false为PHP接口
    public void setCallBack(boolean flag, RequestCallBack callBack) {
        this.callBack = callBack;
        if (flag) {
            request();
        } else {
            requestPhp();
        }
    }

    private void request() {
        show();
        VolleyRequest.RequestPost(url, tag, json, new VolleyInterface() {
            @Override
            public void onMySuccess(HttpResponse response) {

                cancel();
                if (response != null) {
                    if (response.getJavaCode().equals("1")) {
                        JSONObject dataJson = JSON.parseObject(response.getData());
                        HttpResponse.e(dataJson.toString());
                        if (callBack != null) {
                            callBack.onSuccess(dataJson);
                        }
                    } else {
                        String msg = response.getJavaMsg();
                        if (StringUtil.isEmpty(msg)) {
                            ToastUtil.showShort("信息获取失败，请重试");
                        } else {
                            ToastUtil.showShort(msg);
                        }
                    }
                }
            }

            @Override
            public void onMyError(VolleyError arg0) {
                cancel();
                if (callBack != null) {
                    callBack.onError(arg0);
                }
            }
        });
    }

    private void requestPhp() {
        show();
        VolleyRequest.RequestPost(url, tag, json, new VolleyInterface() {
            @Override
            public void onMySuccess(HttpResponse response) {
                cancel();
                if (response != null) {
                    if (response.getCode().equals("200")) {
                        JSONObject jb = JSON.parseObject(response.getData());
                        HttpResponse.e(jb.toString());
                        callBack.onSuccess(jb);
                    } else {
                        if (response.getMsg() != null) {
                            if (response.getMsg().contains("请完善")){
                                callBack.onSuccess(null);
                            }else{
                                ToastUtil.showShort(response.getMsg());
                            }
                        }
                    }
                }
            }

            @Override
            public void onMyError(VolleyError arg0) {
                cancel();
                if (callBack != null) {
                    callBack.onError(arg0);
                }

                if (arg0 != null && arg0.networkResponse != null && arg0.networkResponse.statusCode == 500) {
                    ToastUtil.showShort("服务器异常");
                }else if (arg0 != null && arg0.getMessage() != null&& arg0.getMessage().contains("ConnectException")){
                    ToastUtil.showShort("请检查网络");
                } else {
                    ToastUtil.showShort(arg0.getMessage());
                }
            }
        });
    }

    public void cancel() {
        if (dialog != null) {
            dialog.dismiss();
        }

        if (mRrefreshLayout != null) {
            mRrefreshLayout.finishRefresh();
            mRrefreshLayout.finishRefreshLoadMore();
        }
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }
}