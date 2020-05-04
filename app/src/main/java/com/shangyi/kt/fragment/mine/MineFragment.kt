package com.shangyi.kt.fragment.mine

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.ErrorCallback
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentMineBinding
import com.shangyi.kt.fragment.mine.adapter.*
import com.shangyi.kt.fragment.mine.bean.MineBean
import com.shangyi.kt.fragment.mine.bean.MineImgTxBean
import com.shangyi.kt.fragment.mine.model.MineModel
import com.shangyi.kt.fragment.mine.weight.CenterLayoutManager
import com.shangyi.kt.ui.mine.collect.CollectActivity
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
        mBinding.fragment = this
    }

    override fun initObserve() {
        mBinding.vm?.mineInfo?.observe(this, Observer {
            if (it == null) {
                mLoadService.showCallback(ErrorCallback::class.java)
                mLoadService.showSuccess()
            } else {
                mLoadService.showSuccess()
                bandData(it)
            }
        })
    }

    val titleList = arrayListOf("精选推荐", "生鲜集市", "线上菜市场", "精致女性", "无惧年龄", "出行助手", "居家必备", "开学必备")
    val fragmentList = ArrayList<Fragment>()
    private var mineFragmentAdapter: MineFragmentAdapter? = null  // 个人中心商品推荐的 fragment 适配器
    private var mineHorTjAdapter: MineHorTjAdapter? = null  // 个人中心商品推荐的 横向 推荐分类适配器

    override fun initView() {
        topViewPadding(topLine)
        rvOrdercenter.layoutManager = GridLayoutManager(context, 5)
        rvOrdercenter.adapter = MineOrderCenterAdapter(MineImgTxBean.getOrderData())
        rvVipCenter.layoutManager = GridLayoutManager(context, 4)
        rvVipCenter.adapter = MineVipCenterAdapter(MineImgTxBean.getVipData())
        var centerLayoutManager = CenterLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvHorTj.layoutManager = centerLayoutManager
        mineHorTjAdapter = MineHorTjAdapter(Color.parseColor("#F1190D"), Color.parseColor("#8B8B8B"))
        rvHorTj.adapter = mineHorTjAdapter
        mineHorTjAdapter?.setOnItemScroClick(object : MineHorTjAdapter.OnItemScroClick {
            override fun itemSelect(position: Int) {
                centerLayoutManager.smoothScrollToPosition(rvHorTj, RecyclerView.State(), position)
                viewPager.currentItem = position
                startActivity(Intent(context, CollectActivity::class.java))
            }
        })

        glideImageView.loadImage("")

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                centerLayoutManager.smoothScrollToPosition(rvHorTj, RecyclerView.State(), position)
                mineHorTjAdapter?.setItemSelect(position)
            }
        })
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
        glideImageView.loadImage(it.avatar ?: "")
        when (it.grade) {
            1 -> ivLevel.setImageResource(R.drawable.icon_vip_zs_logo)
            2 -> ivLevel.setImageResource(R.drawable.icon_vip_bj_logo)
            3 -> ivLevel.setImageResource(R.drawable.icon_vip_pt_logo)
            else -> ivLevel.setImageResource(R.drawable.icon_vip_pt_logo)
        }

        if (mineFragmentAdapter == null) {
            titleList.clear()
            fragmentList.clear()
            it.recommend.forEach {
                titleList.add(it.name)
                fragmentList.add(MineTjDataFragment.newInstance(it.id))
            }

            mineFragmentAdapter = MineFragmentAdapter(parentFragmentManager, fragmentList)
            viewPager.adapter = mineFragmentAdapter
            viewPager.offscreenPageLimit = fragmentList.size
            mineHorTjAdapter?.setList(titleList)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.txText -> {
                // 提现

            }
            R.id.ivSetting -> {
                // 设置
                startActivity(Intent(context, SettingActivity::class.java))
            }
            R.id.ivMessage -> {
                // 消息
            }
        }
    }
}