package com.sdxxtop.webview.remotewebview.javascriptinterface;

import android.content.Context;

/**
 * Date:2020/5/15
 * author:lwb
 * Desc:
 */
public interface JavascriptCommand {
    void exec(Context context, String cmd, String params);
}
