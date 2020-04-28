package com.shangyi.kt.ui.splash

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivitySplashBinding
import com.shangyi.kt.ui.MainActivity
import com.shangyi.kt.ui.splash.model.SplashModel

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
                if (mBinding.vm?.settingData?.value == true){
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        }
    }
}
