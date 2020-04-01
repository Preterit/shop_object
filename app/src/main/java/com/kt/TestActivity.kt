package com.kt

import com.kt.model.TestViewModel
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityTestBinding

class TestActivity : BaseKTActivity<ActivityTestBinding, TestViewModel>() {

    override fun layoutId() = R.layout.activity_test
    override fun vmClazz() = TestViewModel::class.java
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initView() {

    }

    override fun initObserve() {
    }
}
