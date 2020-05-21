package com.shangyi.kt.ui.mine.mine_vip

import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sdxxtop.base.BaseNormalActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityPointsShopBinding
import com.shangyi.kt.ui.mine.mine_vip.adapter.PointsShopItemAdapter
import kotlinx.android.synthetic.main.activity_points_shop.*

class PointsShopActivity : BaseNormalActivity<ActivityPointsShopBinding>() {

    override fun layoutId() = R.layout.activity_points_shop

    override fun initView() {
        recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        var adapter = PointsShopItemAdapter()
        recyclerview.adapter = adapter

        val headerView = View.inflate(this,R.layout.pointshop_top_hander_view, null)
        adapter.addHeaderView(headerView)
    }
}
