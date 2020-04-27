package com.shangyi.kt.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentHomeBinding
import com.shangyi.business.databinding.FragmentMineBinding
import com.shangyi.kt.fragment.adapter.MineOrderCenterAdapter
import com.shangyi.kt.fragment.adapter.MineVipCenterAdapter
import com.shangyi.kt.fragment.bean.MineImgTxBean
import com.shangyi.kt.fragment.model.HomeModel
import com.shangyi.kt.fragment.model.MineModel
import com.shangyi.kt.ui.userlogin.model.LoginModel
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class MineFragment : BaseKTFragment<FragmentMineBinding, MineModel>() {

    override fun vmClazz() = MineModel::class.java
    override fun layoutId() = R.layout.fragment_mine
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
        mBinding.vm?.mineInfo?.observe(this, Observer {
            mLoadService.showSuccess()
        })
    }

    override fun initView() {
        topViewPadding(topLine)
        rvOrdercenter.layoutManager = GridLayoutManager(context, 5)
        rvOrdercenter.adapter = MineOrderCenterAdapter(MineImgTxBean.getOrderData())
        rvVipCenter.layoutManager = GridLayoutManager(context, 4)
        rvVipCenter.adapter = MineVipCenterAdapter(MineImgTxBean.getVipData())

        val beginTransaction = childFragmentManager.beginTransaction()
        beginTransaction.add(R.id.frameLayout, lookMoreFragment).commit()

        glideImageView.loadImage("")
    }

    companion object {
        fun newInstance(): MineFragment {
            val args = Bundle()
            val fragment = MineFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initData() {
        mBinding.vm?.loadMineInfo()
    }

    /**
     * 查看更多的fragment
     */
    private val lookMoreFragment: LookMoreFragment by lazy {
        LookMoreFragment()
    }
}