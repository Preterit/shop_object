package com.shangyi.kt.ui.goods

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityGoodsListBinding
import com.shangyi.business.utils.ViewUtil
import com.shangyi.kt.ui.goods.adapter.GoodsListAdapter
import com.shangyi.kt.ui.goods.model.GoodsListModel
import kotlinx.android.synthetic.main.activity_goods_list.*

class GoodsListActivity : BaseKTActivity<ActivityGoodsListBinding, GoodsListModel>() {

    override fun layoutId() = R.layout.activity_goods_list
    override fun vmClazz() = GoodsListModel::class.java
    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    private var categroyId = 0 // 商品分类ID
    private var adapter = GoodsListAdapter()   // 商品适配器
    private var order = "sales" // 排序方式  sales——销量，price——价格
    private var order_value = "" // 排序值(order为price存在，asc-由低到高，desc-由高到低)
    private var pageSize = 0   // 分页加载

    override fun initObserve() {
        mBinding.vm?.goodsList?.observe(this, Observer {
            if (it != null) {
                if (pageSize == 0) {
                    adapter.setList(it)
                } else {
                    adapter.addData(it)
                }
            }
        })
    }


    override fun initView() {
        ViewUtil.topViewPadding(topView)
        categroyId = intent.getIntExtra("categroyId", 0)
        tvXiaoliang.isSelected = true
        tvPrice.isSelected = false
        ivPriceOrder.setImageResource(R.drawable.icon_goodslist_price_all)

        recyclerview.layoutManager = GridLayoutManager(this, 2)
        recyclerview.adapter = adapter
        smartLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                pageSize = adapter.itemCount
                mBinding.vm?.loadGoodsData(categroyId, pageSize, order, order_value, false)
                refreshLayout.finishRefresh()
                refreshLayout.finishLoadMore()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                pageSize = 0
                mBinding.vm?.loadGoodsData(categroyId, pageSize, order, order_value, false)
                refreshLayout.finishRefresh()
                refreshLayout.finishLoadMore()
            }
        })
    }

    override fun initData() {
        mBinding.vm?.loadGoodsData(categroyId, pageSize, order, order_value, true)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ivBack -> {  // 返回
                finish()
            }
            R.id.tvXiaoliang -> {  // 销量
                tvXiaoliang.isSelected = true
                tvPrice.isSelected = false
                order = "sales"
                pageSize = 0
                if (order_value != "") {
                    ivPriceOrder.setImageResource(R.drawable.icon_goodslist_price_all)
                    order_value = ""
                }
                initData()
            }
            R.id.tvPrice -> {  // 价格排序
                tvPrice.isSelected = true
                tvXiaoliang.isSelected = false
                order = "price"
                if (order_value == "asc") {
                    order_value = "desc"  //desc-由高到低
                    ivPriceOrder.setImageResource(R.drawable.icon_goodslist_price_down)
                } else {
                    order_value = "asc"
                    ivPriceOrder.setImageResource(R.drawable.icon_goodslist_price_up)
                }
                pageSize = 0
                initData()
            }
        }
    }
}
