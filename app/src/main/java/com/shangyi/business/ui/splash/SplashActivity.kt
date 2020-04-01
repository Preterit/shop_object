package com.shangyi.business.ui.splash

import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivitySplashBinding
import com.shangyi.business.ui.splash.model.SplashModel

class SplashActivity : BaseKTActivity<ActivitySplashBinding, SplashModel>() {
    override fun vmClazz() = SplashModel::class.java
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {

    }

    override fun layoutId() = R.layout.activity_splash

    override fun initView() {

    }

    override fun initData() {
        mBinding.vm?.loadSettingInfo()
    }
}
