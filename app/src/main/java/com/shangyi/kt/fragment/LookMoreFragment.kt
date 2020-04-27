package com.shangyi.kt.fragment

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.EmptyCallback
import com.sdxxtop.base.loadsir.ErrorCallback
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentLookMoreBinding
import com.shangyi.kt.fragment.adapter.CarListAdatper
import com.shangyi.kt.fragment.adapter.GoodsLookMoreAdapter
import com.shangyi.kt.fragment.model.LookMoreModel
import com.shangyi.kt.ui.goods.GoodsDetailActivity
import com.shangyi.kt.ui.goods.adapter.GoodsDetailLookmoreAdapter
import com.shangyi.kt.ui.goods.bean.ReecommendGood
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
                    mLoadService.showCallback(EmptyCallback::class.java)
                } else {
                    adapter.setList(it)
                    mLoadService.showSuccess()
                }
            } else {
                mLoadService.showCallback(ErrorCallback::class.java)
            }
        })
    }

    var adapter = GoodsLookMoreAdapter()

    override fun initView() {
        recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerview.adapter = adapter

        adapter.setOnItemClickListener { adapter, view, position ->
            val item = adapter.data[position] as ReecommendGood?
            val intent = Intent(context, GoodsDetailActivity::class.java)
            intent.putExtra("goodsId", item?.id)
            startActivity(intent)
        }
    }

    override fun initData() {
        mBinding.vm?.loadLookMoreData()
    }

    override fun preLoad() {
        initData()
    }

}