package com.shangyi.kt.ui.mine.mine_vip

import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityMySyBinding
import com.shangyi.kt.ui.mine.mine_vip.adapter.MySyAdapter
import com.shangyi.kt.ui.mine.mine_vip.model.ShouyiModel
import kotlinx.android.synthetic.main.activity_my_sy.*

class MySyActivity : BaseKTActivity<ActivityMySyBinding, ShouyiModel>() {
    override fun vmClazz() = ShouyiModel::class.java
    override fun layoutId() = R.layout.activity_my_sy
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
    }

    override fun initView() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = MySyAdapter()
    }

    override fun initData() {
        mBinding.vm?.getShouyi()
    }

}
