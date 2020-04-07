package com.shangyi.kt.fragment

import android.os.Bundle
import com.sdxxtop.base.BaseKTFragment
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentHomeBinding
import com.shangyi.kt.fragment.model.HomeModel

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