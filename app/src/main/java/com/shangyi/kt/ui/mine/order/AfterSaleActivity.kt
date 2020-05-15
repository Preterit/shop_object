package com.shangyi.kt.ui.mine.order

import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityAfterSaleBinding
import com.shangyi.kt.ui.mine.order.model.AfterSaleModel

class AfterSaleActivity : BaseKTActivity<ActivityAfterSaleBinding, AfterSaleModel>() {

    override fun vmClazz() = AfterSaleModel::class.java
    override fun layoutId() = R.layout.activity_after_sale

    override fun bindVM() {
    }

    override fun initObserve() {
    }

    override fun initView() {
        val afterSaleFragment = OrderListFragment.newInstence(7)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, afterSaleFragment).commitAllowingStateLoss()
    }
}
