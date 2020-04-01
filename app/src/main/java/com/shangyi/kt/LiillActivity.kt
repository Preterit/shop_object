package com.shangyi.kt

import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityLiillBinding

/**
 * 样板。
 */
class LiillActivity : BaseKTActivity<ActivityLiillBinding, LLLModel>() {
    override fun vmClazz() = LLLModel::class.java
    override fun layoutId() = R.layout.activity_liill

    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
    }


    override fun initView() {
    }

}
