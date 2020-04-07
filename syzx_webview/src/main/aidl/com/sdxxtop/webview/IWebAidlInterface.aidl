package com.sdxxtop.webview;


import com.sdxxtop.webview.IWebAidlCallback;

interface IWebAidlInterface {

      void handleWebAction(int level, String actionName, String jsonParams, in IWebAidlCallback callback);
}
