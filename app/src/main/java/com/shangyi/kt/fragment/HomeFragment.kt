package com.shangyi.kt.fragment

import android.content.Intent
import android.os.Bundle
import com.sdxxtop.base.BaseKTFragment
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentHomeBinding
import com.shangyi.kt.fragment.model.HomeModel
import com.shangyi.kt.ui.order.OrderActivity
import com.shangyi.kt.ui.personal.SettingActivity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class HomeFragment : BaseKTFragment<FragmentHomeBinding, HomeModel>() {

    override fun vmClazz() = HomeModel::class.java
    override fun layoutId() = R.layout.fragment_home
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
    }


    override fun initView() {
        mLoadService.showSuccess()
        home.setOnClickListener {
            startActivity(Intent(context, OrderActivity::class.java)) }

    }

    companion object {
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

}