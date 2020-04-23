package com.shangyi.kt.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.EmptyCallback
import com.sdxxtop.base.loadsir.ErrorCallback
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentLookMoreBinding
import com.shangyi.kt.fragment.adapter.CarListAdatper
import com.shangyi.kt.fragment.model.LookMoreModel
import com.shangyi.kt.ui.goods.adapter.GoodsDetailLookmoreAdapter
import kotlinx.android.synthetic.main.fragment_look_more.*

/**
 * Date:2020/4/23
 * author:lwb
 * Desc:
 */
class LookMoreFragment : BaseKTFragment<FragmentLookMoreBinding, LookMoreModel>() {
    override fun vmClazz() = LookMoreModel::class.java
    override fun layoutId() = R.layout.fragment_look_more
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
        mBinding.vm?.lookMoreData?.observe(this, Observer {
            if (it != null) {
                if (it.isEmpty()) {
//                    mLoadService.showCallback(EmptyCallback::class.java)
                    mLoadService.showSuccess()
                } else {
                    mLoadService.showSuccess()
                }
            } else {
                mLoadService.showCallback(ErrorCallback::class.java)
            }
        })
    }

    override fun initView() {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = CarListAdatper()
    }

    override fun initData() {
        mBinding.vm?.loadLookMoreData()
    }

}