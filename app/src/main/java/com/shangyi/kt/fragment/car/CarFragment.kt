package com.shangyi.kt.fragment.car

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.ErrorCallback
import com.sdxxtop.base.loadsir.LoadingCallback
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentCarBinding
import com.shangyi.kt.fragment.other.lookmore.LookMoreFragment
import com.shangyi.kt.fragment.car.entity.CartInfo
import com.shangyi.kt.fragment.car.weight.NestedExpandaleListView
import com.shangyi.kt.fragment.car.model.CarModel
import com.shangyi.kt.fragment.car.model.OnCarDataRefresh
import com.shangyi.kt.ui.order.AffirmOrderActivity
import kotlinx.android.synthetic.main.car_bottom_buy_layout.view.*
import kotlinx.android.synthetic.main.car_bottom_caozuo_layout.view.*
import kotlinx.android.synthetic.main.car_bottom_caozuo_layout.view.cbCaozuo
import kotlinx.android.synthetic.main.fragment_car.*
import kotlinx.android.synthetic.main.fragment_car.titleView
import kotlinx.android.synthetic.main.item_car_nogoods_layout.view.*


/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CarFragment : BaseKTFragment<FragmentCarBinding, CarModel>(), OnCarDataRefresh {

    override fun vmClazz() = CarModel::class.java
    override fun layoutId() = R.layout.fragment_car
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    private var carListData: List<CartInfo?>? = null  // 购物车数据
    private var exAdapter: CartExpandAdapter? = null  // 购物车列表

    override fun initObserve() {

    }

    /**
     * 查看更多的fragment
     */
    private val lookMoreFragment: LookMoreFragment by lazy {
        LookMoreFragment()
    }

    /**
     * 购买的layout
     */
    private val bottomBuyLayout: View by lazy {
        val view = LayoutInflater.from(context).inflate(R.layout.car_bottom_buy_layout, null, false)
        view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        view.tvPay.setOnClickListener(this)
        view.cbLayoutBuy.setOnClickListener {
            view.cbBuy.isEnabled = !view.cbBuy.isEnabled
            exAdapter?.selectAll(view.cbBuy.isEnabled)
        }
        view.cbBuy.isEnabled = false
        view
    }

    /**
     * 操作的layout
     */
    private val bottomCzLayout: View by lazy {
        val view = LayoutInflater.from(context).inflate(R.layout.car_bottom_caozuo_layout, null, true)
        view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        view.tvDelete.setOnClickListener(this)
        view.cbLayoutCz.setOnClickListener {
            view.cbCaozuo.isEnabled = !view.cbCaozuo.isEnabled
            exAdapter?.selectAll(view.cbCaozuo.isEnabled)
        }
        view.cbCaozuo.isEnabled = false
        view
    }

    /**
     * 购物车为空的布局
     */
    private val noGoodsLayout: View by lazy {
        val inflate = LayoutInflater.from(context).inflate(R.layout.item_car_nogoods_layout, null, false)
        inflate.tvGoShoping.setOnClickListener(this)
        inflate
    }

    /**
     * 购物车有数据的布局
     */
    private val expandableListView: NestedExpandaleListView by lazy {
        val expendList = NestedExpandaleListView(context)
        expendList.setGroupIndicator(null)
        expendList.divider = null
        expendList.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        expendList
    }

    override fun initView() {
        topViewPadding(topLine)
        val beginTransaction = childFragmentManager.beginTransaction()
        beginTransaction.add(R.id.frameLayout, lookMoreFragment).commit()

        bottomLayout.removeAllViews()
        bottomLayout.addView(bottomBuyLayout)

        mBinding.vm?.setOnCarDataRefreshListener(this)
        titleView.tvRight.setOnClickListener(this)
    }

    companion object {
        fun newInstance(): CarFragment {
            val args = Bundle()
            val fragment = CarFragment()
            fragment.arguments = args
            return fragment
        }
    }

    fun initData1() {
        mLoadService.showCallback(LoadingCallback::class.java)
        mBinding.vm?.getCarList()
    }

    override fun initData() {
        initData1()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            initData1()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tview_right -> {
                bottomLayout.removeAllViews()
                bottomCzLayout.cbCaozuo.isEnabled = false
                bottomBuyLayout.cbBuy.isEnabled = false
                exAdapter?.clearSelect()
                // 管理
                if (titleView.tvRight.text == "管理") {
                    frameLayout.visibility = View.GONE
                    titleView.tvRight.text = "完成"
                    bottomLayout.addView(bottomCzLayout)
                } else {
                    frameLayout.visibility = View.VISIBLE
                    titleView.tvRight.text = "管理"
                    bottomLayout.addView(bottomBuyLayout)
                }
            }
            R.id.tvPay -> {
                // 提交订单
                commitOrder()
            }
            R.id.tvDelete -> {
                // 清空
                editDel()
            }
        }
    }

    /**
     * 确认订单
     */
    private fun commitOrder() {
        var selectGoods = exAdapter?.selectGoods
        if (selectGoods.isNullOrEmpty()) {
            Toast.makeText(context, "请选择需要支付的商品", Toast.LENGTH_SHORT).show()
            return
        }
        var intent = Intent(context, AffirmOrderActivity::class.java)
        intent.putExtra("orderData", selectGoods)
        startActivity(intent)
    }

    /**
     * 管理清楚
     */
    private fun editDel() {
        val selectId = exAdapter?.selectId
        if (selectId?.size == 0) {
            UIUtils.showToast("选择需要删除的商品")
            return
        }
        mBinding.vm?.delGoods(selectId, true)
    }

    /**
     * 绑定数据
     */
    private fun bandData() {
        if (exAdapter == null) {
            exAdapter = CartExpandAdapter(context, carListData)
            expandableListView.setAdapter(exAdapter)
            expandableListView.setOnGroupClickListener { _, _, _, _ -> true }
            exAdapter?.setAdapterClickListener(object : CartExpandAdapter.OnAdapterClickListener {
                override fun moneyRefresh(money: Float) {
                    bottomBuyLayout.tvPrice.text = "$money"
                }

                override fun childAddCutClick(type: Int, groupPosition: Int, childPosition: Int, count: Int) {
                    if (type == 1) {
                        UIUtils.showToast("商品增加到${count}")
                    } else {
                        UIUtils.showToast("商品减少到${count}")
                    }
                }

                override fun delectClick(isEmpty: Boolean, cid: IntArray?) {
                    mBinding.vm?.delGoods(cid?.asList(), false)
                    if (isEmpty) {
                        linearLayout.removeView(expandableListView)
                        linearLayout.addView(noGoodsLayout, 0)
                    }
                }
            })
        } else {
            exAdapter?.refreshData(carListData)
            exAdapter?.refreshMoney()
        }
        val intgroupCount: Int = carListData?.size ?: 0
        for (i in 0 until intgroupCount) {
            expandableListView.collapseGroup(i)
            expandableListView.expandGroup(i)
        }
        exAdapter?.notifyDataSetChanged()
    }

    /**
     * 更新购物车数据的回掉
     */
    override fun carDataRefresh(it: List<CartInfo?>?) {
        bottomBuyLayout.cbBuy.isEnabled = false
        if (it != null) {
            if (it.isEmpty()) {
                linearLayout.removeAllViews()
                linearLayout.addView(noGoodsLayout, 0)
                mLoadService.showSuccess()
            } else {
                carListData = it
                bandData()
                linearLayout.removeAllViews()
                linearLayout.addView(expandableListView, 0)
                mLoadService.showSuccess()
            }
        } else {
            mLoadService.showCallback(ErrorCallback::class.java)
        }
    }

    override fun preLoad() {
        initData1()
    }
}