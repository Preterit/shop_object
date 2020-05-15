package com.shangyi.kt.fragment.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.sdxxtop.base.BaseKTFragment
import com.sdxxtop.base.loadsir.LoadingCallback
import com.sdxxtop.webview.command.Command
import com.sdxxtop.webview.command.CommandsManager
import com.sdxxtop.webview.command.ResultBack
import com.sdxxtop.webview.utils.WebConstants
import com.shangyi.business.R
import com.shangyi.business.databinding.FragmentHomeBinding
import com.shangyi.kt.fragment.categroy.adapter.BannerDataBean
import com.shangyi.kt.fragment.home.adapter.HomeBannerBean
import com.shangyi.kt.fragment.home.adapter.HomeBottomAdapter
import com.shangyi.kt.fragment.home.adapter.HomeTopBanner
import com.shangyi.kt.fragment.home.model.HomeModel
import com.shangyi.kt.ui.WebActivity
import com.shangyi.kt.ui.home.activity.*
import com.shangyi.kt.ui.setting.HomeJkfyActivity
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
import pl.droidsonroids.gif.GifImageView
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
                    bannerList.add(HomeBannerBean(bannerItem.img_url, bannerItem.page_url, bannerItem.name))
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
    private var banner: Banner<BannerDataBean, HomeTopBanner>? = null  // 轮播图banner
    private var bannerAdapter = HomeTopBanner()   // 轮播图适配器
    private var bannerList = ArrayList<HomeBannerBean>()  // 轮播图数据集合

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
        topView.findViewById<LinearLayout>(R.id.home_gaoyong).setOnClickListener(this)
        topView.findViewById<LinearLayout>(R.id.home_jingxuan).setOnClickListener(this)
        topView.findViewById<GifImageView>(R.id.homeGifImg).setOnClickListener(this)
        topView.findViewById<ImageView>(R.id.home_pinpai_one).setOnClickListener(this)
        topView.findViewById<ImageView>(R.id.home_pinpai_two).setOnClickListener(this)
        topView.findViewById<ImageView>(R.id.home_pinpai_three).setOnClickListener(this)
        topView.findViewById<ImageView>(R.id.img_jiangkangketang).setOnClickListener(this)

        adapter.addHeaderView(topView)

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            topSearchLayout.setBackgroundColor(changeAlpha(resources.getColor(R.color.color_ff2941), abs(verticalOffset * 1.0f) / appBarLayout.totalScrollRange))
        })


        banner = mBinding.root.findViewById(R.id.banner)
        if (banner != null) {
            bannerAdapter.setDatas(arrayListOf())
            banner!!.setAdapter(bannerAdapter).setIndicator(CircleIndicator(context)).start()
        }
        //设置点击事件
        banner?.setOnBannerListener(OnBannerListener { data: Any, position: Int ->
            if (position == 0) {
                val item = data as HomeBannerBean
//                var intent = Intent(context, HomeBannerWebActivity::class.java)
//                intent.putExtra("url",item.page_url)
//                startActivity(intent)
                CommandsManager.getInstance().registerCommand(WebConstants.LEVEL_BASE, goodsDetailCommand)
                WebActivity.startCommonWeb(context, item.name, item.page_url, 1)
            }
        })
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
        when (v.id) {
            //高佣专区001
            R.id.home_gaoyong -> {
                startActivity(Intent(context, HomeGaoyongActivity::class.java))
            }
            //每周精选002
            R.id.home_jingxuan -> {
                startActivity(Intent(context, HomeJingXuanActivity::class.java))
            }
            //健康防疫003
            R.id.home_jkfy -> {
                startActivity(Intent(context, HomeJkfyActivity::class.java))
            }
            //好课推荐004
            R.id.homeGifImg -> {
                startActivity(Intent(context, HomeHaoKefyActivity::class.java))
            }
            //品牌好物005
            R.id.home_pinpai_one -> {
                startActivity(Intent(context, HomePinpaiActivity::class.java))
            }
            //品牌好物
            R.id.home_pinpai_two -> {
                startActivity(Intent(context, HomePinpaiActivity::class.java))
            }
            //品牌好物
            R.id.home_pinpai_three -> {
                startActivity(Intent(context, HomePinpaiActivity::class.java))
            }
            //健康课堂
            R.id.img_jiangkangketang -> {
                startActivity(Intent(context, HomeJKClassroomActivity::class.java))
            }

        }
    }

    /**
     * 页面路由
     */
    private val goodsDetailCommand = object : Command {
        override fun exec(context: Context?, params: MutableMap<Any?, Any?>?, resultBack: ResultBack?) {
            Log.e("dialogCommand -- ", "" + params.toString())
        }

        override fun name(): String {
            return "goodsDetail"
        }
    }
}

