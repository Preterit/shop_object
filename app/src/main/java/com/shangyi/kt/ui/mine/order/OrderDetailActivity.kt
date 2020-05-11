package com.shangyi.kt.ui.mine.order

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityOrderDetailBinding
import com.shangyi.kt.ui.mine.order.adapter.OrderListFragmentAdapter
import com.shangyi.kt.ui.order.adapter.OrderDetailGoodsAdapter
import com.shangyi.kt.ui.order.bean.OrderDetailInfoBean
import com.shangyi.kt.ui.order.model.OrderDetailModel
import kotlinx.android.synthetic.main.activity_order_detail.*

class OrderDetailActivity : BaseKTActivity<ActivityOrderDetailBinding, OrderDetailModel>() {

    override fun layoutId() = R.layout.activity_order_detail
    override fun vmClazz() = OrderDetailModel::class.java

    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
        mBinding.vm?.orderInfo?.observe(this, Observer {
            if (it != null) {
                bandData(it)
            }
        })
    }

    private var orderNum = ""   // 订单编号
    private var adapter = OrderDetailGoodsAdapter()

    override fun initView() {
        orderNum = intent.getStringExtra(OrderListFragmentAdapter.ORDER_LIST_ID_BUNDLE_KEY) ?: ""

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    override fun initData() {
        mBinding.vm?.loadOrderInfo(orderNum)
    }

    /**
     * 显示数据
     */
    private fun bandData(it: OrderDetailInfoBean) {
        glideImageView.loadImage(it.shop?.shop_avatar ?: "")
        adapter.setList(it.order_goods)
    }

}
