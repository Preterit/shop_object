package com.shangyi.kt.ui.mine.mine_vip

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.EmptyCallback
import com.sdxxtop.base.loadsir.ErrorCallback
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentYhqViewBinding
import com.shangyi.kt.ui.mine.bean.Number
import com.shangyi.kt.ui.mine.mine_vip.adapter.YhqItemAdapter
import com.shangyi.kt.ui.mine.mine_vip.model.YhqModel
import kotlinx.android.synthetic.main.fragment_yhq_view.*

/**
 * Date:2020/5/7
 * author:lwb
 * Desc:
 */
class YhqItemFragment : BaseKTFragment<FragmentYhqViewBinding, YhqModel>() {

    override fun vmClazz() = YhqModel::class.java
    override fun layoutId() = R.layout.fragment_yhq_view

    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
        mBinding.vm?.yhqData?.observe(this, Observer {
            if (activity is YhqActivity) {
                val yhqActivity = activity as YhqActivity
                yhqActivity.setTabLayoutTx(it?.number)
            }
            if (it?.data == null) {
                mLoadService.showCallback(ErrorCallback::class.java)
            } else {
                mLoadService.showSuccess()
                if (it.data.isEmpty()) {
                    mLoadService.showCallback(EmptyCallback::class.java)
                } else {
                    if (page == 0) {
                        adapter.setList(it.data)
                    } else {
                        adapter.addData(it.data)
                    }
                }
            }
        })
        mBinding.vm?.delSuccess?.observe(this, Observer {
            if (it) {
                UIUtils.showToast("删除成功")
                initData()
            }
        })
    }

    private var type = 0  // 优惠券类型
    private var page = 0  // 分页标识
    private val adapter = YhqItemAdapter()

    override fun initView() {
        type = arguments?.getInt("type") ?: 0

        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter

        adapter.setOnItemChildClick(object : YhqItemAdapter.OnItemChildClick {
            override fun delItem(id: Int?) {
                mBinding.vm?.delYhq(id)
            }
        })

        smartLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page = adapter.data.size
                initData()
                refreshLayout.finishLoadMore()
                refreshLayout.finishRefresh()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                initData()
                refreshLayout.finishLoadMore()
                refreshLayout.finishRefresh()
            }
        })
    }

    companion object {
        fun newInstence(type: Int): YhqItemFragment {
            val fragment = YhqItemFragment()
            val bundle = Bundle()
            bundle.putInt("type", type)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initData() {
        mBinding.vm?.loadYhuData(type, page)
    }

    override fun preLoad() {
        initData()
    }
}