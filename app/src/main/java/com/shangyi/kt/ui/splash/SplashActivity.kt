package com.shangyi.kt.ui.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.Observer
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivitySplashBinding
import com.shangyi.business.network.Constants
import com.shangyi.business.network.SpUtil
import com.shangyi.kt.ui.MainActivity
import com.shangyi.kt.ui.splash.model.SplashModel
import com.tbruyelle.rxpermissions2.RxPermissions

class SplashActivity : BaseKTActivity<ActivitySplashBinding, SplashModel>() {
    override fun vmClazz() = SplashModel::class.java
    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    // 等待时间 完成
                    isDelayTime = true
                    startActivity()
                }
            }
        }
    }

    fun startActivity() {
        if (isLoadPermissionsOver && isDelayTime) {
            if (isGuide) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, GuideActivity::class.java))
                finish()
            }
        }
    }

    private var isLoadPermissionsOver = false // 是否加载全部权限
    private var isLoadSettingInfo = false // 是否加载配置信息
    private var isDelayTime = false       // 是否过了延迟打开时间
    private var isGuide = false            // 是否已经过了引导页

    override fun initObserve() {
        mBinding.vm?.settingData?.observe(this, Observer {
            if (it) {
                isLoadSettingInfo = true
            }
        })
    }

    override fun layoutId() = R.layout.activity_splash

    @SuppressLint("CheckResult")
    override fun initView() {
        isGuide = SpUtil.getBoolean(Constants.FIRST_OPEN, false)

        val rxPermissions = RxPermissions(this)
        rxPermissions
                .request(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE)
                .subscribe { granted: Boolean ->
                    if (granted) { // Always true pre-M
                        Log.e("rxPermissions -- ", "${granted}")
                        // 所有的权限申请完成的回掉
                        isLoadPermissionsOver = true
                        startActivity()
                    } else {
                        // 权限全部申请结束的回掉  总的回掉
                        isLoadPermissionsOver = true
                        startActivity()
                    }
                }

        mHandler.sendEmptyMessageDelayed(1, 3000)
    }

    override fun initData() {
        mBinding.vm?.loadSettingInfo()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
    }
}
