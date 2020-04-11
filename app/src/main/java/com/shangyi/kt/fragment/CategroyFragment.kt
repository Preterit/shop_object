package com.shangyi.kt.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.ErrorCallback
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

    private val adapter = CategroyLeftAdapter(Color.parseColor("#8B8B8B"), Color.parseColor("#FF2941"))

    /**
     * 初始化右侧数据fragment
     */
    private val categroyRightFragment: CategroyRightFragment by lazy {
        val categroyRightFragment = CategroyRightFragment.newInstance()
        categroyRightFragment
    }

    override fun initObserve() {
        mBinding.vm?.categoryLeftData?.observe(this, Observer {
            if (null != it) {
                mLoadService.showSuccess()
                adapter.replaceData(it)
                if (it.isNotEmpty()) {
                    categroyRightFragment.loadCategroyRightData(it[0].id)
                    adapter.setSelectPosition(0)
                }
            } else {
                mLoadService.showCallback(ErrorCallback::class.java)
            }
        })
    }

    override fun initView() {
        /**
         * 左侧分类点击事件
         */
        adapter.setOnCategroyItemClick(object : CategroyLeftAdapter.OnItemClickListener {
            override fun onItemClick(categroyId: Int) {
                categroyRightFragment.loadCategroyRightData(categroyId)
            }
        })

        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adapter
        childFragmentManager.beginTransaction().replace(R.id.frameLayout, categroyRightFragment).commitAllowingStateLoss()
    }


    override fun initData() {
        mBinding.vm?.getLeftCategory()
    }

//    override fun onResume() {
//        super.onResume()
//        if (isVisible) {
//            mBinding.vm?.getLeftCategory()
//        }
//    }
//
//    override fun onHiddenChanged(hidden: Boolean) {
//        super.onHiddenChanged(hidden)
//        if (!hidden) {
//            mBinding.vm?.getLeftCategory()
//        }
//    }

}