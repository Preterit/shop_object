package com.shangyi.kt.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentCategroyRightBinding
import com.shangyi.kt.fragment.adapter.CategroyRightAdapter
import com.shangyi.kt.fragment.model.CategroyModel
import kotlinx.android.synthetic.main.fragment_categroy_right.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CategroyRightFragment : BaseKTFragment<FragmentCategroyRightBinding, CategroyModel>() {
    override fun layoutId() = R.layout.fragment_categroy_right
    override fun vmClazz() = CategroyModel::class.java
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {

    }

    companion object {
        fun newInstance(): CategroyRightFragment {
            val args = Bundle()
            val fragment = CategroyRightFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView() {
        mLoadService.showSuccess()
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = CategroyRightAdapter()
    }


}