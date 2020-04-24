package com.shangyi.kt.fragment.car

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.marginBottom
import androidx.lifecycle.Observer
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.ErrorCallback
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentCarBinding
import com.shangyi.kt.fragment.LookMoreFragment
import com.shangyi.kt.fragment.car.entity.CartInfo
import com.shangyi.kt.fragment.car.weight.NestedExpandaleListView
import com.shangyi.kt.fragment.model.CarModel
import com.shangyi.kt.fragment.model.OnCarDataRefresh
import io.reactivex.internal.operators.maybe.MaybeIsEmpty
import kotlinx.android.synthetic.main.car_bottom_buy_layout.view.*
import kotlinx.android.synthetic.main.car_bottom_caozuo_layout.view.*
import kotlinx.android.synthetic.main.fragment_car.*
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
        view.checkboxLayout.setOnClickListener(this)

        view
    }

    /**
     * 操作的layout
     */
    private val bottomCzLayout: View by lazy {
        val view = LayoutInflater.from(context).inflate(R.layout.car_bottom_caozuo_layout, null, true)
        view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        view.tvDelete.setOnClickListener(this)
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
        mBinding.vm?.getCarList()
    }

    override fun onResume() {
        super.onResume()
        if (isVisible) {
            initData1()
        }
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
                UIUtils.showToast("提交订单")
            }
            R.id.checkboxLayout -> {
                // 购买全选
                UIUtils.showToast("购买全选")
            }
            R.id.tvDelete -> {
                // 清空
                UIUtils.showToast("清空")
                editDel()
            }
        }
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
}