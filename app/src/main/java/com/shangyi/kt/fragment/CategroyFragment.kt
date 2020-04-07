package com.shangyi.kt.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentCategroyBinding
import com.shangyi.business.databinding.FragmentHomeBinding
import com.shangyi.kt.fragment.adapter.CategroyLeftAdapter
import com.shangyi.kt.fragment.model.HomeModel
import com.shangyi.kt.ui.userlogin.model.LoginModel
import kotlinx.android.synthetic.main.fragment_categroy.*

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CategroyFragment : BaseKTFragment<FragmentCategroyBinding, HomeModel>() {

    override fun vmClazz() = HomeModel::class.java
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

    override fun initObserve() {
    }


    override fun initView() {
        mLoadService.showSuccess()
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = CategroyLeftAdapter(Color.parseColor("#8B8B8B"),Color.parseColor("#FF2941"))
    }


}