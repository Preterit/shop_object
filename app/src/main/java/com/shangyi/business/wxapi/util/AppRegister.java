package com.shangyi.business.wxapi.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.shangyi.business.api.Constom;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Date:2020/4/29
 * author:lwb
 * Desc:
 */
public class AppRegister extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);

        // 将该app注册到微信
        msgApi.registerApp(Constom.WXAPP_ID);
    }
}
