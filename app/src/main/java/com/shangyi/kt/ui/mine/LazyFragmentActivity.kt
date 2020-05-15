package com.shangyi.kt.ui.mine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.shangyi.business.R
import com.shangyi.kt.ui.mine.order.OrderListFragment
import com.shangyi.kt.ui.mine.order.adapter.MineOrderFragmentAdapter
import kotlinx.android.synthetic.main.activity_all_order.*

class LazyFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lazy_fragment)
    }

    fun initView() {

        val list = arrayListOf<String>("全部", "待付款", "待发货", "待收货", "待评价")
        val fragmentList = arrayListOf<Fragment>()
        list.forEachIndexed { index, _ ->
            fragmentList.add(OrderListFragment.newInstence(index))
        }
        val adapter = MineOrderFragmentAdapter(supportFragmentManager, fragmentList, list)
        viewPager.adapter = adapter
        tablayout.setupWithViewPager(viewPager)

    }
}
