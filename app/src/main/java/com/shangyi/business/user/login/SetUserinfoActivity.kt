package com.shangyi.business.user.login

import android.view.View
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivitySetUserinfoBinding


class SetUserinfoActivity : BaseKTActivity<ActivitySetUserinfoBinding, LoginModel>() {

    override fun vmClazz() = LoginModel::class.java
    override fun layoutId() = R.layout.activity_set_userinfo

    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    override fun initObserve() {

    }


    override fun initView() {
        intent?.getStringExtra("registerphone") ?: ""
        intent?.getStringExtra("password") ?: ""
    }

    override fun onClick(v: View) {

    }


}
