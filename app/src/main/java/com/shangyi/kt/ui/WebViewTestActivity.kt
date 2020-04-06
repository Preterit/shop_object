package com.shangyi.kt.ui

import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityWebViewTestBinding
import com.shangyi.kt.ui.userlogin.model.LoginModel
import kotlinx.android.synthetic.main.activity_web_view_test.*

class WebViewTestActivity : BaseKTActivity<ActivityWebViewTestBinding, LoginModel>() {

    override fun vmClazz() = LoginModel::class.java

    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
    }

    override fun layoutId() = R.layout.activity_web_view_test

    override fun initView() {
        tvClick.setOnClickListener {
            mBinding.vm?.loadWebViewData()
        }
    }

    override fun initData() {

    }
}
