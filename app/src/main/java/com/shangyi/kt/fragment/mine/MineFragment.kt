package com.shangyi.kt.fragment.mine

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.ErrorCallback
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentMineBinding
import com.shangyi.kt.fragment.other.lookmore.LookMoreFragment
import com.shangyi.kt.fragment.mine.adapter.MineOrderCenterAdapter
import com.shangyi.kt.fragment.mine.adapter.MineVipCenterAdapter
import com.shangyi.kt.fragment.mine.bean.MineBean
import com.shangyi.kt.fragment.mine.bean.MineImgTxBean
import com.shangyi.kt.fragment.mine.model.MineModel
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
            if (it == null) {
                mLoadService.showCallback(ErrorCallback::class.java)
            } else {
                mLoadService.showSuccess()
                bandData(it)
            }
        })
    }

    /**
     * 查看更多的fragment
     */
    private val lookMoreFragment: LookMoreFragment by lazy {
        LookMoreFragment()
    }

    override fun initView() {
        topViewPadding(topLine)
        rvOrdercenter.layoutManager = GridLayoutManager(context, 5)
        rvOrdercenter.adapter = MineOrderCenterAdapter(MineImgTxBean.getOrderData())
        rvVipCenter.layoutManager = GridLayoutManager(context, 4)
        rvVipCenter.adapter = MineVipCenterAdapter(MineImgTxBean.getVipData())

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

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            initData()
        }
    }

    /**
     * 异常重试
     */
    override fun preLoad() {
        initData()
    }

    /**
     * 绑定数据
     */
    private fun bandData(it: MineBean) {
        val beginTransaction = childFragmentManager.beginTransaction()
        beginTransaction.add(R.id.frameLayout, lookMoreFragment).commit()

        glideImageView.loadImage(it.avatar ?: "")
        when (it.grade) {
            1 -> ivLevel.setImageResource(R.drawable.icon_vip_zs_logo)
            2 -> ivLevel.setImageResource(R.drawable.icon_vip_bj_logo)
            3 -> ivLevel.setImageResource(R.drawable.icon_vip_pt_logo)
            else -> ivLevel.setImageResource(R.drawable.icon_vip_pt_logo)
        }
    }
}