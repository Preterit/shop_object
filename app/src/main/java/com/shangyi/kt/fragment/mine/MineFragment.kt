package com.shangyi.kt.fragment.mine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sdxxtop.base.BaseKTFragment
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentMineBinding
import com.shangyi.kt.fragment.mine.adapter.AdapterFragment
import com.shangyi.kt.fragment.mine.adapter.DynamicFragment
import com.shangyi.kt.fragment.mine.adapter.MineOrderCenterAdapter
import com.shangyi.kt.fragment.mine.adapter.MineVipCenterAdapter
import com.shangyi.kt.fragment.mine.bean.MineBean
import com.shangyi.kt.fragment.mine.bean.MineImgTxBean
import com.shangyi.kt.fragment.mine.model.MineModel
import com.shangyi.kt.fragment.other.lookmore.LookMoreFragment
import com.shangyi.kt.ui.setting.SettingActivity
import kotlinx.android.synthetic.main.fragment_mine.*


/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class MineFragment : BaseKTFragment<FragmentMineBinding, MineModel>() {

    override fun vmClazz() = MineModel::class.java
    override fun layoutId() = R.layout.fragment_mine
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
        mBinding.vm?.mineInfo?.observe(this, Observer {
            if (it == null) {
                mLoadService.showSuccess()
//                mLoadService.showCallback(ErrorCallback::class.java)
            } else {
                mLoadService.showSuccess()
                bandData(it)
            }
        })
    }

    private var isAddTj = false  //  推荐列表是否添加到布局中
    private var toolBarPositionY = 0  //  推荐列表是否添加到布局中

    /**
     * 查看更多的fragment
     */
    private val lookMoreFragment: LookMoreFragment by lazy {
        LookMoreFragment()
    }

    override fun initView() {
        topViewPadding(topLine)
        rvOrdercenter.layoutManager = GridLayoutManager(context, 5)
        rvOrdercenter.adapter = MineOrderCenterAdapter(MineImgTxBean.getOrderData())
        rvVipCenter.layoutManager = GridLayoutManager(context, 4)
        rvVipCenter.adapter = MineVipCenterAdapter(MineImgTxBean.getVipData())

        ivSetting.setOnClickListener { startActivity(Intent(context,SettingActivity::class.java)) }
        glideImageView.loadImage("")

        viewPager.adapter = AdapterFragment(parentFragmentManager, getFragments())
        viewPager.offscreenPageLimit = 10

//        nestedScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
//            val location = IntArray(2)
//            stopLayout.getLocationOnScreen(location)
//            val xPosition = location[0]
//            val yPosition = location[1]
//            if (yPosition < topLayout.bottom) {
//                stopTopLayout.visibility = View.VISIBLE
//                nestedScrollView.setNeedScroll(false)
//                Log.e("stopTopLayout--false---", "stopTopLayout == visibility")
//            } else {
//                Log.e("stopTopLayout--true---", "stopTopLayout == INVISIBLE")
//                stopTopLayout.visibility = View.INVISIBLE
//                nestedScrollView.setNeedScroll(true)
//            }
//        }
//
//        stopLayout.post(Runnable { dealWithViewPager() })
    }

    private fun dealWithViewPager() {
        toolBarPositionY = stopTopLayout.bottom
        val params = viewPager.layoutParams
        params.height = getScreenHeightPx(context) - toolBarPositionY + 1
        viewPager.layoutParams = params
    }

    companion object {
        fun newInstance(): MineFragment {
            val args = Bundle()
            val fragment = MineFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initData() {
        mBinding.vm?.loadMineInfo()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            initData()
        }
    }

    /**
     * 异常重试
     */
    override fun preLoad() {
        initData()
    }

    /**
     * 绑定数据
     */
    private fun bandData(it: MineBean) {
        if (!isAddTj) {
            val beginTransaction = childFragmentManager.beginTransaction()
            beginTransaction.add(R.id.frameLayout, lookMoreFragment).commit()
            isAddTj = true
        }

        glideImageView.loadImage(it.avatar ?: "")
        when (it.grade) {
            1 -> ivLevel.setImageResource(R.drawable.icon_vip_zs_logo)
            2 -> ivLevel.setImageResource(R.drawable.icon_vip_bj_logo)
            3 -> ivLevel.setImageResource(R.drawable.icon_vip_pt_logo)
            else -> ivLevel.setImageResource(R.drawable.icon_vip_pt_logo)
        }
    }


    /**
     * @return 获取屏幕的高 单位：px
     */
    fun getScreenHeightPx(context: Context?): Int {
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        if (windowManager != null) {
//            windowManager.getDefaultDisplay().getMetrics(dm);
            windowManager.defaultDisplay.getRealMetrics(dm)
            return dm.heightPixels
        }
        return 0
    }

    private fun getFragments(): List<Fragment>? {
        val fragments: MutableList<Fragment> = ArrayList()
        for (i in 0..3) {
            fragments.add(DynamicFragment.newInstance())
        }
        return fragments
    }
}