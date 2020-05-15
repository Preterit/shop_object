package com.sdxxtop.webview.remotewebview.javascriptinterface;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

/**
 * Date:2020/5/15
 * author:lwb
 * Desc:
 */
public abstract class BaseJavascriptInterface {

    private Context mContext;
    private JavascriptCommand javascriptCommand;
    private final Handler mHandler = new Handler();

    public BaseJavascriptInterface(Context context) {
        this.mContext = context;
    }

    public void post(final String cmd, final String param) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (javascriptCommand != null) {
                        javascriptCommand.exec(mContext, cmd, param);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setJavascriptCommand(JavascriptCommand javascriptCommand) {
        this.javascriptCommand = javascriptCommand;
    }

}
