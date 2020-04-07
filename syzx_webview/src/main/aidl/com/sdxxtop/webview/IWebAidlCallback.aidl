// IWebAidlCallback.aidl
package com.sdxxtop.webview;


interface IWebAidlCallback {
    void onResult(int responseCode, String actionName, String response);
}
