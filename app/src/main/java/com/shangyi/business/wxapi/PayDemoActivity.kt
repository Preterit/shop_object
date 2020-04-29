package com.shangyi.business.wxapi

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.shangyi.business.R
import com.shangyi.business.api.Constom
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXImageObject
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import java.io.ByteArrayOutputStream


class PayDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_demo)

        findViewById<Button>(R.id.pay).setOnClickListener {
//            getOrderInfo()
            shareWebpage("上医在线分享","测试上医在线分享功能。。","www.baidu.com",SendMessageToWX.Req.WXSceneSession)
        }
        api.registerApp(Constom.WXAPP_ID)
    }

    /**
     * 微信支付api
     */
    private val api: IWXAPI by lazy {
        var api = WXAPIFactory.createWXAPI(this, null)
        api.registerApp(Constom.WXAPP_ID)
        api
    }

    fun getOrderInfo() {
        val req = PayReq()
//        req.appId = it.appid
//        req.partnerId = it.partnerid
//        req.prepayId = it.prepayid
//        req.nonceStr = it.nonce_str
//        req.timeStamp = "${it.timestamp}"
//        req.packageValue = it.`package`
//        req.sign = it.sign
//        req.extData = "app data" // optional
        req.appId = "wxb4ba3c02aa476ea1"
        req.partnerId = "1900006771"
        req.prepayId = "wx2916175767156917c2529b841538607932"
        req.nonceStr = "5d6e2c9fd111de5c27b7aa5f072d1233"
        req.timeStamp = "1588148277"
        req.packageValue = "Sign=WXPay"
        req.sign = "D24F4B174F2EA72619621661D47BC8CC"
        req.extData = "app data" // optional
        api.sendReq(req)
    }

    fun shareWx() {
//        val bmp = BitmapFactory.decodeResource(resources, R.drawable.duck)
//        //初始化 WXImageObject 和 WXMediaMessage 对象
//        val imgObj = WXImageObject(bmp)
//        val msg = WXMediaMessage()
//        msg.mediaObject = imgObj
//
//        //设置缩略图
//        val thumbBmp = Bitmap.createScaledBitmap(bmp, 50, 50, true)
//        bmp.recycle()
//        msg.thumbData = bmpToByteArray(thumbBmp, true)
//
//        //构造一个Req
//        val req = SendMessageToWX.Req()
//        req.transaction = buildTransaction("img")
//        req.message = msg
//        req.scene = mTargetScene
//        req.userOpenId = getOpenId()
//        //调用api接口，发送数据到微信
//        api.sendReq(req)
    }

    fun bmpToByteArray(bmp: Bitmap, needRecycle: Boolean): ByteArray? {
        val output = ByteArrayOutputStream()
        bmp.compress(CompressFormat.PNG, 100, output)
        if (needRecycle) {
            bmp.recycle()
        }
        val result: ByteArray = output.toByteArray()
        try {
            output.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun shareWebpage(title: String?, content: String?, weburl: String?, scene: Int) {
        val webpage = WXWebpageObject()
        webpage.webpageUrl = weburl
        val msg = WXMediaMessage(webpage)
        msg.title = title
        msg.description = content
        val bmp = BitmapFactory.decodeResource(resources, R.drawable.duck)
        val thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true)
        bmp.recycle()
        msg.thumbData = bmpToByteArray(thumbBmp, true)
        val req = SendMessageToWX.Req()
        req.transaction = buildTransaction("webpage")
        req.message = msg
        req.scene = scene
        api.sendReq(req)
    }

    private fun buildTransaction(type: String?): String? {
        return if (type == null) System.currentTimeMillis().toString() else type + System.currentTimeMillis()
    }
}

