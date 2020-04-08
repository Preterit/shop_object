package com.shangyi.kt.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentCategroyBinding
import com.shangyi.kt.fragment.adapter.CategroyLeftAdapter
import com.shangyi.kt.fragment.model.CategroyModel
import kotlinx.android.synthetic.main.fragment_categroy.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CategroyFragment : BaseKTFragment<FragmentCategroyBinding, CategroyModel>() {

    override fun vmClazz() = CategroyModel::class.java
    override fun layoutId() = R.layout.fragment_categroy
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    companion object {
        fun newInstance(): CategroyFragment {
            val args = Bundle()
            val fragment = CategroyFragment()
            fragment.arguments = args
            return fragment
        }
    }

    /**
     * 初始化右侧数据fragment
     */
    private val categroyRightFragment: CategroyRightFragment by lazy {
        val categroyRightFragment = CategroyRightFragment.newInstance()
        categroyRightFragment
    }

    override fun initObserve() {

    }

    override fun initView() {
        mLoadService.showSuccess()
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = CategroyLeftAdapter(Color.parseColor("#8B8B8B"), Color.parseColor("#FF2941"))

        childFragmentManager.beginTransaction().replace(R.id.frameLayout, categroyRightFragment).commitAllowingStateLoss()
    }


    override fun onResume() {
        super.onResume()
        if (isVisible) {
            mBinding.vm?.getCategory()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            mBinding.vm?.getCategory()
        }
    }

}