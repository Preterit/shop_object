package com.shangyi.kt.fragment

import android.os.Bundle
import com.sdxxtop.base.BaseKTFragment
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentHomeBinding
import com.shangyi.business.databinding.FragmentMineBinding
import com.shangyi.kt.fragment.model.HomeModel
import com.shangyi.kt.ui.userlogin.model.LoginModel

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class MineFragment : BaseKTFragment<FragmentMineBinding, HomeModel>() {

    override fun vmClazz() = HomeModel::class.java
    override fun layoutId() = R.layout.fragment_mine
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
    }


    override fun initView() {
    }

    companion object {
        fun newInstance(): MineFragment {
            val args = Bundle()
            val fragment = MineFragment()
            fragment.arguments = args
            return fragment
        }
    }

}