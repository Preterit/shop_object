package com.shangyi.kt.ui.mine.mine_vip

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityCollectBinding
import com.shangyi.kt.ui.mine.bean.Good
import com.shangyi.kt.ui.mine.mine_vip.model.CollectModel
import kotlinx.android.synthetic.main.activity_collect.*

class CollectActivity : BaseKTActivity<ActivityCollectBinding, CollectModel>() {
    override fun layoutId() = R.layout.activity_collect
    override fun vmClazz() = CollectModel::class.java

    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
        mBinding.vm?.collectData?.observe(this, Observer {
            if (it != null) {
                if (page == 0) {
                    adapter.setList(it)
                } else {
                    adapter.addData(it)
                }
            } else {

            }
        })
    }

    override fun initData() {
        mBinding.vm?.loadCollectData(page)
    }

    private val adapter = CollectListAdapter()   //  收藏列表适配器
    private var page = 0 // 分页标识
    private var delList = ArrayList<Good>()

    override fun initView() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        smartLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page = adapter.data.size
                initData()
                refreshLayout.finishRefresh()
                refreshLayout.finishLoadMore()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                initData()
                refreshLayout.finishRefresh()
                refreshLayout.finishLoadMore()
            }
        })

        titleView.tvRight.setOnClickListener {
            var isEdit = titleView.tvRight.text == "管理"
            smartLayout.setEnableRefresh(!isEdit)
            smartLayout.setEnableLoadMore(!isEdit)
            titleView.tvRight.text = if (isEdit) "完成" else "管理"
            bottomLayout.visibility = if (isEdit) View.VISIBLE else View.GONE
            checkboxActivity.isEnabled = false

            adapter.data.forEach {
                it?.isEdit = isEdit
                it?.isSelect = false
                it?.goods?.forEach { good ->
                    good.isEdit = isEdit
                    good.isSelect = false
                }
            }
            adapter.notifyDataSetChanged()
        }

        adapter.setOnItemDelect(object : CollectListAdapter.OnItemDelect {
            override fun oneItemDel(goods: List<Good>) {
                mBinding.vm?.delCollect(goods)
            }
        })

        delectAll.setOnClickListener {
            delGoods()
        }

        checkboxLayout.setOnClickListener {
            checkboxActivity.isEnabled = !checkboxActivity.isEnabled
            adapter.data.forEach {
                it?.isSelect = checkboxActivity.isEnabled
                it?.goods?.forEach { good ->
                    good.isSelect = checkboxActivity.isEnabled
                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    /**
     * 批量删除收藏商品
     */
    private fun delGoods() {
        var iterator = adapter.data.iterator()
        while (iterator.hasNext()) {
            var it = iterator.next()
            val goods = it?.goods?.iterator()
            while (goods?.hasNext()!!) {
                val next = goods.next()
                if (next.isSelect) {
                    delList.add(next)
                    goods.remove()
                }
            }
            if (it?.goods?.size == 0) {
                iterator.remove()
            }
            adapter.notifyDataSetChanged()
        }
        mBinding.vm?.delCollect(delList)
    }
}
