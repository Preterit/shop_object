package com.shangyi.kt.ui.goods

import androidx.recyclerview.widget.GridLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityGoodsListBinding
import com.shangyi.business.utils.ViewUtil
import com.shangyi.kt.ui.goods.adapter.GoodsListAdapter
import com.shangyi.kt.ui.goods.model.GoodsListModel
import kotlinx.android.synthetic.main.activity_goods_list.*

class GoodsListActivity : BaseKTActivity<ActivityGoodsListBinding, GoodsListModel>() {

    override fun layoutId() = R.layout.activity_goods_list
    override fun vmClazz() = GoodsListModel::class.java
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
    }


    override fun initView() {
        ViewUtil.topViewPadding(topView)

        recyclerview.layoutManager = GridLayoutManager(this, 2)
        recyclerview.adapter = GoodsListAdapter()

        smartLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
            }
        })
    }
}
