package com.shangyi.kt.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.ErrorCallback
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentCarBinding
import com.shangyi.kt.fragment.adapter.CarListAdatper
import com.shangyi.kt.fragment.bean.CarDataBean
import com.shangyi.kt.fragment.model.CarModel
import kotlinx.android.synthetic.main.fragment_car.*

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
                bandData(it)
            } else {
                mLoadService.showCallback(ErrorCallback::class.java)
            }
        })
    }

    override fun initView() {
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = CarListAdatper()
    }

    companion object {
        fun newInstance(): CarFragment {
            val args = Bundle()
            val fragment = CarFragment()
            fragment.arguments = args
            return fragment
        }
    }

    fun initData1() {
        mBinding.vm?.getCarList()
    }

    override fun onResume() {
        super.onResume()
        if (isVisible) {
            initData1()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            initData1()
        }
    }

    /**
     * 绑定数据
     */
    private fun bandData(it: List<CarDataBean?>) {

    }
}