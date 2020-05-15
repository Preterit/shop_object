package com.sdxxtop.webview.remotewebview.javascriptinterface;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * 1. 保留command的注册
 * 2. 不支持command通过远程aidl方式调用
 */
public final class JavascriptInterfaceSybk extends BaseJavascriptInterface {

    public JavascriptInterfaceSybk(Context context) {
        super(context);
    }

    @JavascriptInterface
    public void goodsDetail(final String param) {
        post("goodsDetail", param);
    }
}
