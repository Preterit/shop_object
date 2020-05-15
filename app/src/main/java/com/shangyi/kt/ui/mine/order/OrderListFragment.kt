package com.shangyi.kt.ui.mine.order

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.BaseLazyFragment
import com.sdxxtop.base.loadsir.ErrorCallback
import com.sdxxtop.base.loadsir.LoadingCallback
import com.sdxxtop.base.loadsir.OrderListEmptyCallback
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentOrderListBinding
import com.shangyi.kt.ui.mine.order.adapter.OrderListFragmentAdapter
import com.shangyi.kt.ui.mine.order.model.OrderListFragmentModel
import kotlinx.android.synthetic.main.fragment_order_list.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Date:2020/5/6
 * author:lwb
 * Desc:
 */
class OrderListFragment : BaseLazyFragment<FragmentOrderListBinding, OrderListFragmentModel>() {

    override fun vmClazz() = OrderListFragmentModel::class.java
    override fun layoutId() = R.layout.fragment_order_list

    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
        mBinding.vm?.data?.observe(this, Observer {
            if (it == null) {
                mLoadService.showCallback(ErrorCallback::class.java)
            } else {
                if (it.isEmpty() && adapter.data.size == 0) {
                    mLoadService.showCallback(OrderListEmptyCallback::class.java)
                } else {
                    mLoadService.showSuccess()
                    if (page == 0) {  // 刷新
                        adapter.setList(it)
                    } else {
                        adapter.addData(it)
                    }
                }
            }
        })
        mBinding.vm?.confirmReceipt?.observe(this, Observer {
            if (it) {
                onFragmentResume()
            }
        })
    }

    private val adapter = OrderListFragmentAdapter(this)
    private var page = 0  // 分页开始量
    private var type = 0  // 订单分类

    override fun initView() {
        type = arguments?.getInt("type", 0) ?: 0
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter

        smartLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page = adapter.data.size
                onFragmentResume()
                refreshLayout.finishLoadMore()
                refreshLayout.finishRefresh()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                onFragmentResume()
                refreshLayout.finishLoadMore()
                refreshLayout.finishRefresh()
            }
        })
    }

    companion object {
        fun newInstence(type: Int): OrderListFragment {
            val fragment = OrderListFragment()
            val bundle = Bundle()
            bundle.putInt("type", type)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun preLoad() {
        onFragmentResume()
    }

    override fun onFragmentFirstVisible() {
        mLoadService.showCallback(LoadingCallback::class.java)
    }

    override fun onFragmentResume() {
        mBinding.vm?.loadOrderListData(page, type - 1)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: String?) {
        if (event.equals("0")) {
            UIUtils.showToast("支付成功")
            onFragmentResume()
        }
    }

}