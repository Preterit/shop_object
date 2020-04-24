package com.shangyi.kt.ui.order

import android.util.Log
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityAffirmOrderBinding
import com.shangyi.kt.fragment.car.entity.CommitOrderBean
import com.shangyi.kt.ui.order.adapter.OrderGoodsAdapter
import com.shangyi.kt.ui.order.model.CommitOrderModel
import kotlinx.android.synthetic.main.activity_affirm_order.*
import java.util.ArrayList

class AffirmOrderActivity : BaseKTActivity<ActivityAffirmOrderBinding, CommitOrderModel>() {
    override fun vmClazz() = CommitOrderModel::class.java
    override fun layoutId() = R.layout.activity_affirm_order

    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {

    }

    private var orderData: ArrayList<CommitOrderBean>? = null  // 商品的数据

    override fun initView() {
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        orderData = intent.getSerializableExtra("orderData") as ArrayList<CommitOrderBean>
                ?: ArrayList()

        Log.e("orderData --- ", "${orderData.toString()}")

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = OrderGoodsAdapter(orderData)
    }

}
