package com.shangyi.kt.ui.order

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityAffirmOrderBinding
import com.shangyi.kt.fragment.car.entity.CommitOrderBean
import com.shangyi.kt.ui.address.AddressListActivity
import com.shangyi.kt.ui.address.bean.AreaListBean
import com.shangyi.kt.ui.order.adapter.OrderGoodsAdapter
import com.shangyi.kt.ui.order.bean.OrderListJsonBean
import com.shangyi.kt.ui.order.model.CommitOrderModel
import kotlinx.android.synthetic.main.activity_affirm_order.*
import kotlinx.android.synthetic.main.item_goods_detail_goodsinfo.view.*
import java.util.ArrayList

class AffirmOrderActivity : BaseKTActivity<ActivityAffirmOrderBinding, CommitOrderModel>() {
    override fun vmClazz() = CommitOrderModel::class.java
    override fun layoutId() = R.layout.activity_affirm_order

    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    override fun initObserve() {
        mBinding.vm?.areaBean?.observe(this, Observer {
            if (it != null) {
                tvAdsStr.text = "${it.provice?.name}${it.city?.name}${it.county?.name}${it.detail}"
                addressId = it.id
                mBinding.vm?.loadYunfei(list, addressId)
            }
        })
    }

    private var orderData: ArrayList<CommitOrderBean>? = null  // 商品的数据
    private var addressId = 0   // 地址ID
    private var list = ArrayList<OrderListJsonBean>()  // 请求接口的商品列表

    override fun initView() {
        orderData = intent.getSerializableExtra("orderData") as ArrayList<CommitOrderBean>
                ?: ArrayList()

        Log.e("orderData --- ", "${orderData.toString()}")

        if (orderData == null) {
            return
        }
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = OrderGoodsAdapter(orderData)

        var totalPrice = 0f
        for (item in orderData!!) {
            item.goodsInfos?.forEach {
                val goodsItem = OrderListJsonBean(it.goodsId, it.goodsSpecId, it.goodsCount, it.goodsImg
                        ?: "")
                list.add(goodsItem)
            }
            list[list.size - 1].remark = item.psText ?: ""
            // 总金额相加
            totalPrice += item.totalPrice
        }
        tvPrice.text = "¥$totalPrice"
        tvTotalPrice.text = "¥$totalPrice"
    }

    override fun initData() {
        mBinding.vm?.loadAddress()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.adsLayout -> {
                // 选择地址
                startActivityForResult(Intent(this@AffirmOrderActivity, AddressListActivity::class.java), 11)
            }
            R.id.tvCommotOrder -> {
                // 提交订单
                commitOrder()
            }
        }
    }

    /**
     * 提交订单
     */
    private fun commitOrder() {
//        list.clear()
//        for (item in orderData!!) {
//            item.goodsInfos?.forEach {
//                val goodsItem = OrderListJsonBean(it.goodsId, it.goodsSpecId, it.goodsCount, it.goodsImg
//                        ?: "")
//                list.add(goodsItem)
//            }
//            list[list.size - 1].remark = item.psText ?: ""
//        }
        mBinding.vm?.commitOrder(list, addressId)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) return@onActivityResult
        if (requestCode == 11) {
            val item = data.getParcelableExtra<AreaListBean?>("areaBean")
            mBinding.vm?.areaBean?.value = item
        }
    }
}
