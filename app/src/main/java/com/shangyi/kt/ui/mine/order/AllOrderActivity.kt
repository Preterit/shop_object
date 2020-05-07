package com.shangyi.kt.ui.mine.order

import androidx.fragment.app.Fragment
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityAllOrderBinding
import com.shangyi.kt.ui.mine.order.adapter.MineOrderFragmentAdapter
import com.shangyi.kt.ui.mine.order.model.MineOrderModel
import kotlinx.android.synthetic.main.activity_all_order.*

class AllOrderActivity : BaseKTActivity<ActivityAllOrderBinding, MineOrderModel>() {

    override fun vmClazz() = MineOrderModel::class.java
    override fun layoutId() = R.layout.activity_all_order

    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
    }

    private var currentItem = 0

    override fun initView() {

        currentItem = intent.getIntExtra("currentItem", 0)

        val list = arrayListOf<String>("全部", "待付款", "待发货", "待收货", "待评价")
        val fragmentList = arrayListOf<Fragment>()
        list.forEachIndexed { index, _ ->
            fragmentList.add(OrderListFragment.newInstence(index))
        }
        val adapter = MineOrderFragmentAdapter(supportFragmentManager, fragmentList, list)
        viewPager.adapter = adapter
        tablayout.setupWithViewPager(viewPager)

        viewPager.currentItem = currentItem
    }
}
