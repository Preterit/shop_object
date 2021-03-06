package com.sdxxtop.base

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.kingja.loadsir.core.LoadSir
import com.sdxxtop.base.loadsir.*
import com.sdxxtop.network.NetworkSession
import kotlin.properties.Delegates

/**
 * Email: sdxxtop@163.com
 * Created by sdxxtop 2019-07-25 18:01
 * Version: 1.0
 * Description:
 */
abstract class BaseApplication : MultiDexApplication() {

    companion object {
        @JvmStatic
        var INSTANCE: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        NetworkSession.initNetwork(this, versionCode(),isDebug())

//        ConnectivityReceiver.register(this)

        LoadSir.beginBuilder()
                .addCallback(ErrorCallback())
                .addCallback(EmptyCallback())
                .addCallback(LoadingCallback())
                .addCallback(TimeoutCallback())
                .addCallback(CustomCallback())
                .addCallback(OrderListEmptyCallback())
                .setDefaultCallback(LoadingCallback::class.java)
                .commit()
    }

    abstract fun isDebug(): Boolean
    abstract fun versionCode(): Int
}