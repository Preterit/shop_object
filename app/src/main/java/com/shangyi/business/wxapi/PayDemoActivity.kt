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
            shareWebpage("上医在线分享", "测试上医在线分享功能。。", "www.baidu.com", SendMessageToWX.Req.WXSceneSession)
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
        req.appId = "wx8c512b137c836be1"
        req.partnerId = "1560159451"
        req.prepayId = "wx301420486944175b739779901328851800"
        req.nonceStr = "wnkx6c7gc598muyrgdr4x7pfvs9ak67b"
        req.timeStamp = "1588227648"
        req.packageValue = "Sign=WXPay"
        req.sign = "259BB5618FFDE2F5C5641EE5F863505C"
        req.extData = "app data" // optional
        api.sendReq(req)
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

