package com.shangyi.kt.fragment.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.LoadingCallback
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentHomeBinding
import com.shangyi.kt.fragment.categroy.adapter.BannerDataBean
import com.shangyi.kt.fragment.categroy.adapter.CategroyRightBanner
import com.shangyi.kt.fragment.home.adapter.HomeBottomAdapter
import com.shangyi.kt.fragment.home.model.HomeModel
import com.shangyi.kt.ui.setting.HomeJkfyActivity
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_home_top_view.*
import kotlinx.android.synthetic.main.item_home_top_view.view.*
import kotlin.math.abs

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class HomeFragment : BaseKTFragment<FragmentHomeBinding, HomeModel>() {

    override fun vmClazz() = HomeModel::class.java
    override fun layoutId() = R.layout.fragment_home

    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    override fun initObserve() {
        mBinding.vm?.homeBanner?.observe(this, Observer {
            mLoadService.showSuccess()
            if (it != null && it.isNotEmpty()) {
                bannerList.clear()
                it.forEach { bannerItem ->
                    bannerList.add(BannerDataBean(bannerItem.img_url))
                }
                bannerAdapter.setDatas(bannerList)
            }
        })

        mBinding.vm?.listData?.observe(this, Observer {
            mLoadService.showSuccess()
            if (it != null) {
                adapter.setList(it)
            }
        })
    }

    private var adapter = HomeBottomAdapter()   // recyclerview 适配器
    private var banner: Banner<BannerDataBean, CategroyRightBanner>? = null  // 轮播图banner
    private var bannerAdapter = CategroyRightBanner()   // 轮播图适配器
    private var bannerList = ArrayList<BannerDataBean>()  // 轮播图数据集合

    companion object {
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView() {
        mLoadService.showSuccess()
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter

        val topView = LayoutInflater.from(context).inflate(R.layout.item_home_top_view, null, false)
        topView.findViewById<LinearLayout>(R.id.home_jkfy).setOnClickListener(this)
        adapter.addHeaderView(topView)

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            topSearchLayout.setBackgroundColor(changeAlpha(resources.getColor(R.color.color_ff2941), abs(verticalOffset * 1.0f) / appBarLayout.totalScrollRange))
        })


        banner = mBinding.root.findViewById(R.id.banner)
        if (banner != null) {
            bannerAdapter.setDatas(arrayListOf(
                    BannerDataBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588867415798&di=4ba8ba3cfe320c60bf81b91f798523e0&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg"),
                    BannerDataBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588867415797&di=b9b1851d4f679a963d694d2704878990&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F64%2F76%2F20300001349415131407760417677.jpg"),
                    BannerDataBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588867415797&di=2f2605081af02771526848c0800fb64b&imgtype=0&src=http%3A%2F%2Ft7.baidu.com%2Fit%2Fu%3D888206991%2C1760071208%26fm%3D191%26app%3D48%26wm%3D1%2C13%2C90%2C45%2C0%2C7%26wmo%3D10%2C10%26n%3D0%26g%3D0n%26f%3DJPEG%3Fsec%3D1853310920%26t%3D5737bdb8b0a6dccf99f9e8ae009bb4fc")
            ))
            banner!!.setAdapter(bannerAdapter).setIndicator(CircleIndicator(context)).start()
        }
    }

    override fun initData() {
        mLoadService.showCallback(LoadingCallback::class.java)
        mBinding.vm?.getHomeBannerData()
        mBinding.vm?.getListData()
    }

    /**
     * 重试
     */
    override fun preLoad() {
        initData()
    }


    override fun onDestroy() {
        super.onDestroy()
        banner?.stop()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            initData()
        }
    }

    /** 根据百分比改变颜色透明度 */
    private fun changeAlpha(color: Int, fraction: Float): Int {
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        val alpha = (Color.alpha(color) * fraction).toInt()
        return Color.argb(alpha, red, green, blue)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.home_jkfy ->{
                startActivity(Intent(context ,HomeJkfyActivity::class.java))
            }
        }
    }
}

