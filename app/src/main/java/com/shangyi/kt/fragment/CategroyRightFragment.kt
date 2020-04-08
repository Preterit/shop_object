package com.shangyi.kt.fragment

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.EmptyCallback
import com.sdxxtop.base.loadsir.ErrorCallback
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

    /**
     * 适配器
     */
    private var adapter = CategroyRightAdapter()
    private var mCategroyId = 0  // 分类ID

    override fun initObserve() {
        mBinding.vm?.categoryRightData?.observe(this, Observer {
            if (it?.child_list != null) {
                adapter.replaceData(it.child_list)
                if (it.child_list.isEmpty()) mLoadService.showCallback(EmptyCallback::class.java) else mLoadService.showSuccess()
            } else {
                mLoadService.showCallback(ErrorCallback::class.java)
            }
        })
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
        recyclerview.adapter = adapter
    }

    fun loadCategroyRightData(categroyId: Int) {
        mCategroyId = categroyId
        mBinding.vm?.getRightCategory(categroyId)
    }


    /**
     * 重试
     */
    override fun preLoad() {
        mBinding.vm?.getRightCategory(mCategroyId)
    }
}