package com.shangyi.kt.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sdxxtop.base.BaseKTFragment
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentCarBinding
import com.shangyi.business.databinding.FragmentHomeBinding
import com.shangyi.kt.fragment.model.CarModel
import com.shangyi.kt.fragment.model.HomeModel
import com.shangyi.kt.ui.userlogin.model.LoginModel

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CarFragment : BaseKTFragment<FragmentCarBinding, CarModel>() {

    override fun vmClazz() = CarModel::class.java
    override fun layoutId() = R.layout.fragment_car
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
        mBinding.vm?.carList?.observe(this, Observer {
            if (it != null) {
                mLoadService.showSuccess()
            }
        })
    }

    override fun initView() {

    }

    companion object {
        fun newInstance(): CarFragment {
            val args = Bundle()
            val fragment = CarFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initData() {
        mBinding.vm?.getCarList()
    }

}