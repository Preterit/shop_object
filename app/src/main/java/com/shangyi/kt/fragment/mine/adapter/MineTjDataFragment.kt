package com.shangyi.kt.fragment.mine.adapter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.BaseLazyFragment
import com.sdxxtop.base.loadsir.ErrorCallback
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentTestBinding
import com.shangyi.kt.fragment.mine.model.MineModel
import com.shangyi.kt.fragment.other.lookmore.adapter.GoodsLookMoreAdapter
import com.shangyi.kt.ui.goods.GoodsDetailActivity
import com.shangyi.kt.ui.goods.bean.ReecommendGood
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * Date:2020/4/28
 * author:lwb
 * Desc:
 */
class MineTjDataFragment : BaseLazyFragment<FragmentTestBinding, MineModel>() {

    companion object {
        fun newInstance(id: Int): MineTjDataFragment {
            val args = Bundle()
            args.putInt("itemId", id)
            val fragment = MineTjDataFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun vmClazz() = MineModel::class.java
    override fun layoutId() = R.layout.fragment_test

    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
        mBinding.vm?.itemListData?.observe(this, Observer {
            if (it != null) {
                if (it.isEmpty()) {
                    mLoadService.showSuccess()
                    linearLayout.visibility = View.VISIBLE
                } else {
                    mLoadService.showSuccess()
                    linearLayout.visibility = View.GONE
                    adapter.replaceData(it)
                }
            } else {
                mLoadService.showCallback(ErrorCallback::class.java)
            }
        })
    }

    var adapter = GoodsLookMoreAdapter()
    var itemId = 0  // 当前fragment 分类的ID

    override fun initView() {
        itemId = arguments?.getInt("itemId") ?: 0
        smartLayout.setEnableRefresh(false)
        smartLayout.setEnableLoadMore(false)
        smartLayout.setOnLoadMoreListener {
            it.finishLoadMore()
        }
        recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerview.adapter = adapter

        adapter.setOnItemClickListener { adapter, view, position ->
            val item = adapter.data[position] as ReecommendGood
            val intent = Intent(context, GoodsDetailActivity::class.java)
            intent.putExtra("goodsId", item.id)
            startActivity(intent)
        }
    }

    override fun preLoad() {
        initData()
    }

    override fun onFragmentFirstVisible() {
        mBinding.vm?.loadFmListData(itemId)
    }

}