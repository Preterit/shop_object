package com.shangyi.kt.ui.mine.order

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kingja.loadsir.callback.Callback.OnReloadListener
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.sdxxtop.base.BaseKTActivity
import com.sdxxtop.base.loadsir.PlaceholderCallback
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityOrderDetailBinding
import com.shangyi.kt.ui.mine.order.adapter.OrderListFragmentAdapter
import com.shangyi.kt.ui.mine.weight.OrderStatusBtn
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

        setStatusImg(it.status)
    }

    /**
     * 设置  订单状态图片 / 按钮
     *
     * 0、待支付（待付款）    --- （付款）（取消订单）（修改地址）
     * 1、支付成功（待发货）  ---   // 不显示 任何按钮
     * 2、已发货（待收货）    ---  （延长收货）（确认收货）
     * 3、已签收            ---   （评价）
     * 4、订单取消（已取消）  ---
     * 5、订单删除（已删除）
     * 6、申请退款
     * 7、退款成功
     * 8、拒绝退款
     *
     */
    private var btnStrList = arrayListOf<String>()
    private fun setStatusImg(status: Int) {
        bottomBtnLayout.removeAllViews()
        btnStrList.clear()
        when (status) {
            0 -> {
                // 待付款
                ivOrderStatusImg.setImageResource(R.drawable.order_status_pay)
                btnStrList = arrayListOf("修改地址", "取消订单", "付款")
            }
            1 -> {
                // 待发货
                ivOrderStatusImg.setImageResource(R.drawable.order_status_pay_over)
            }
            2 -> {
                // 已发货
                ivOrderStatusImg.setImageResource(R.drawable.order_status_send)
                btnStrList = arrayListOf("延长收货", "确认收货")
            }
            3 -> {
                // 已签收
                ivOrderStatusImg.setImageResource(R.drawable.order_detail_success)
                btnStrList = arrayListOf("评价")
            }

            else -> {
                // 已完成
                ivOrderStatusImg.setImageResource(R.drawable.order_detail_success)
            }
        }

        btnStrList.forEach {
            val bean = OrderStatusBtn(this)
            bean.setData(it)
            bottomBtnLayout.addView(bean)
        }

        if (btnStrList.size == 0) {
            bottomBtnLayout.visibility = View.GONE
        } else {
            bottomBtnLayout.visibility = View.VISIBLE
        }
    }

    /**
     * 返回订单编号。
     */
    fun getOrderNum() = orderNum
}
