package com.shangyi.kt.ui.home.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shangyi.business.R


class HomeBannerWebActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_banner_web)
        val myWebView = findViewById<WebView>(R.id.web_view)
        myWebView.settings.javaScriptEnabled = true
        myWebView.addJavascriptInterface(JavaScriptinterface(this),
                "android")
//        myWebView.setWebViewClient(myWebViewClient())
        myWebView.loadUrl(intent.getStringExtra("url"))
    }


    class JavaScriptinterface {

        var mContext: Context? = null

        constructor (c: Context) {
            mContext = c
        }

        /**
         * 与js交互时用到的方法，在js里直接调用的
         */
        @JavascriptInterface
        fun goodsDetail(ssss: String?) {
            Toast.makeText(mContext, ssss, Toast.LENGTH_LONG).show()
        }
    }
}
