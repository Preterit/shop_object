package com.shangyi.kt.ui.splash

import android.content.Intent
import android.view.View
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivitySplashBinding
import com.shangyi.kt.ui.WebViewTestActivity
import com.shangyi.kt.ui.splash.model.SplashModel
import com.shangyi.kt.ui.userlogin.LoginActivity

class SplashActivity : BaseKTActivity<ActivitySplashBinding, SplashModel>() {
    override fun vmClazz() = SplashModel::class.java
    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    override fun initObserve() {

    }

    override fun layoutId() = R.layout.activity_splash

    override fun initView() {

    }

    override fun initData() {
        mBinding.vm?.loadSettingInfo()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tvClick -> {
                startActivity(Intent(this, WebViewTestActivity::class.java))
                finish()
            }
        }
    }
}
