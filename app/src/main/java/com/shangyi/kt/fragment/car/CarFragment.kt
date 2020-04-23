package com.shangyi.kt.fragment.car

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
import kotlinx.android.synthetic.main.fragment_car.*
import kotlinx.android.synthetic.main.item_car_nogoods_layout.view.*


/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CarFragment : BaseKTFragment<FragmentCarBinding, CarModel>() {

    override fun vmClazz() = CarModel::class.java
    override fun layoutId() = R.layout.fragment_car
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
        mBinding.vm?.carList?.observe(this, Observer {
            if (it != null) {
                if (it.isEmpty()) {
                    linearLayout.removeView(expandableListView)
                    linearLayout.addView(noGoodsLayout, 0)
                    mLoadService.showSuccess()
                } else {
                    bandData(it)
                    linearLayout.removeView(noGoodsLayout)
                    linearLayout.addView(expandableListView, 0)
                    mLoadService.showSuccess()
                }
            } else {
                mLoadService.showCallback(ErrorCallback::class.java)
            }
        })
    }

    /**
     * 查看更多的fragment
     */
    private val lookMoreFragment: LookMoreFragment by lazy {
        LookMoreFragment()
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


    private var exAdapter: CartExpandAdapter? = null

    /**
     * 绑定数据
     */
    private fun bandData(it: List<CartInfo?>) {
        if (exAdapter == null) {
//            val list = arrayListOf<CartInfo>()
//            for (i in 1..10) {
//                val childList = arrayListOf<CartInfo.ItemsBean>()
//                for (j in 1..2) {
//                    childList.add(CartInfo.ItemsBean())
//                }
//                val carInfo = CartInfo()
//                carInfo.items = childList
//                list.add(carInfo)
//            }
            exAdapter = CartExpandAdapter(context, it)
            expandableListView.setAdapter(exAdapter)
            val intgroupCount: Int = expandableListView.count
            for (i in 0 until intgroupCount) {
                expandableListView.expandGroup(i)
            }
            expandableListView.setOnGroupClickListener { _, _, _, _ -> true }
            exAdapter?.setAdapterClickListener(object : CartExpandAdapter.OnAdapterClickListener {
                override fun moneyRefresh(money: Float) {
                    tvPrice.text = "$money"
                }

                override fun childAddCutClick(type: Int, groupPosition: Int, childPosition: Int, count: Int) {
                    if (type == 1) {
                        UIUtils.showToast("商品增加到${count}")
                    } else {
                        UIUtils.showToast("商品减少到${count}")
                    }
                }

                override fun delectClick(groupPosition: Int, childPosition: Int) {
                    UIUtils.showToast("删除第$groupPosition 组中第 $childPosition 个")
                }
            })
        } else {
            exAdapter?.notifyDataSetChanged()
        }
    }
}