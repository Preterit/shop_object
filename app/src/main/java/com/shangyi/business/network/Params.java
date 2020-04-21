package com.shangyi.business.network;

import android.content.Context;
import android.text.TextUtils;

import com.sdxxtop.network.utils.AESUtils;
import com.shangyi.business.api.Constom;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/7.
 */

public class Params {
    protected Context context;
    protected HashMap<String, String> map;

    public Params() {
        map = new HashMap<>();
        context = NetWorkSession.getContext();
        defaultValue();
    }

    private void defaultValue() {
        put("uid", SpUtil.getInt(Constants.USER_ID, 0));
//        put("ui", SpUtil.getInt(Constants.USER_ID, 0));
//        put("pi", SpUtil.getInt(Constants.PART_ID, 0));
//        put("plid", "1");
    }

    public String getUserId() {
        String ui = map.get("ui");
        if (TextUtils.isEmpty(ui)) {
            ui = String.valueOf(SpUtil.getInt(Constants.USER_ID, 0));
        }
        return ui;
    }

    public String getPartId() {
        String pi = map.get("ci");
        if (TextUtils.isEmpty(pi)) {
            pi = String.valueOf(SpUtil.getInt(Constants.PART_ID, 0));
        }
        return pi;
    }

    public void removeKey(String key) {
        if (map.containsKey(key)) {
            map.remove(key);
        }
    }

    public void put(String key, String value) {
        map.put(key, StringUtil.stringNotNull(value));
    }

    public void put(String key, long value) {
        map.put(key, value + "");
    }

    public void put(String key, int value) {
        map.put(key, value + "");
    }

    public String getData() {
        return NetUtil.getBase64Data(map);
    }

    public String getNormalData() {
        return NetUtil.getNormalData(map);
    }

    public String getAESData() {
        String normalData = NetUtil.getNormalData(map);

        String requestData = "";
        try {
            requestData = AESUtils.encrypt(normalData, Constom.API_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestData;
    }

    public void putDeviceNo() {
        map.put("dn", DeviceUtil.getDeviceNo(context));
    }
}
