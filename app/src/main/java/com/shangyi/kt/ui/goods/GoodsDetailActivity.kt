package com.shangyi.kt.ui.goods

import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityGoodsDetailBinding
import com.shangyi.kt.ui.goods.adapter.GoodsDetailLookmoreAdapter
import com.shangyi.kt.ui.goods.adapter.GoodsDetailTjBannerAdapter
import com.shangyi.kt.ui.goods.adapter.GoodsDetailTjBean
import com.shangyi.kt.ui.goods.adapter.MultipleTypesAdapter
import com.shangyi.kt.ui.goods.bean.GoodDetailTopBarBean
import com.shangyi.kt.ui.goods.model.GoodDetailModel
import com.shangyi.kt.ui.goods.weight.GoodDetailTopTitle
import com.shangyi.kt.ui.goods.weight.banner.indicator.NumIndicator
import com.youth.banner.Banner
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_goods_detail.*
import kotlinx.android.synthetic.main.item_goods_detail_goodsdetail.view.*
import kotlinx.android.synthetic.main.item_goods_detail_tuijian.view.*
import java.util.*

class GoodsDetailActivity : BaseKTActivity<ActivityGoodsDetailBinding, GoodDetailModel>() {

    override fun vmClazz() = GoodDetailModel::class.java
    override fun layoutId() = R.layout.activity_goods_detail
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    private var bannerAdapter: MultipleTypesAdapter? = null   // 轮播图适配器
    private var banner: Banner<GoodDetailTopBarBean, MultipleTypesAdapter>? = null  // 商品轮播图
    private var shopTjBanner: Banner<GoodDetailTopBarBean, GoodsDetailTjBannerAdapter>? = null  // 商品推荐轮播图
    private lateinit var goodsInfoView: View
    private lateinit var shopInfoView: View
    private lateinit var goodsDetailView: View
    private lateinit var goodsTjView: View

    private var scrollviewFlag = false
    private var tabIndex = -1

    override fun initObserve() {

    }


    override fun initView() {
        goodsInfoView = LayoutInflater.from(this).inflate(R.layout.item_goods_detail_goodsinfo, null, false)       // 商品信息
        shopInfoView = LayoutInflater.from(this).inflate(R.layout.item_goods_detail_shopinfo, null, false)         // 店铺信息
        goodsDetailView = LayoutInflater.from(this).inflate(R.layout.item_goods_detail_goodsdetail, null, false)   // 商品详情
        goodsTjView = LayoutInflater.from(this).inflate(R.layout.item_goods_detail_tuijian, null, false)           // 推荐商品


        bannerAdapter = MultipleTypesAdapter(this, getTestDataVideo())
        banner = goodsInfoView?.findViewById(R.id.banner)
        banner!!.setAdapter(bannerAdapter!!)
                .setIndicator(NumIndicator(this))
                .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                .start()

        /**
         * 看了又看适配器
         */
        goodsTjView?.goodsDetailLookmoreRecycler.layoutManager = GridLayoutManager(this, 2)
        goodsTjView?.goodsDetailLookmoreRecycler.adapter = GoodsDetailLookmoreAdapter()

        goodsDetailView?.goodsDetailWeb.loadUrl("http://39.106.156.132/service.html")

        /**
         * 店铺推荐
         */
        shopTjBanner = shopInfoView?.findViewById(R.id.shopTjBanner)
        var goodsDetailTjBannerAdapter = GoodsDetailTjBannerAdapter()
        goodsDetailTjBannerAdapter.setDatas(arrayListOf<GoodsDetailTjBean>(
                GoodsDetailTjBean(""),
                GoodsDetailTjBean(""),
                GoodsDetailTjBean("")
        ))
        shopTjBanner!!.setAdapter(goodsDetailTjBannerAdapter)
                .setIndicator(CircleIndicator(this))
                .setIndicatorGravity(IndicatorConfig.Direction.CENTER)


        if (goodsInfoView != null) {
//            goodsInfoLayout.addView(goodsInfoView)
            detailLayout.addView(goodsInfoView)
        }

        if (shopInfoView != null) {
//            shopInfoLayout.addView(shopInfoView)
            detailLayout.addView(shopInfoView)
        }

        if (goodsDetailView != null) {
//            goodsDetailLayout.addView(goodsDetailView)
            detailLayout.addView(goodsDetailView)
        }

        if (goodsTjView != null) {
            detailLayout.addView(goodsTjView)
        }


        //scrollview滑动事件监听
        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            scrollviewFlag = true
            if (scrollY < shopInfoView.top) {
                if (tabIndex != 0) { //增加判断，如果滑动的区域是tableIndex=0对应的区域，则不改变tablayout的状态
                    tabIndex = 0
                    topGoodsTop.setOnItemSelect(0)
                }
            } else if (scrollY >= shopInfoView.top && scrollY < goodsDetailView.top) {
                if (tabIndex != 1) {
                    tabIndex = 1
                    topGoodsTop.setOnItemSelect(1)
                }
            } else if (scrollY >= goodsDetailView.top && scrollY < goodsTjView.top) {
                if (tabIndex != 2) {
                    tabIndex = 2
                    topGoodsTop.setOnItemSelect(2)
                }
            } else if (scrollY >= goodsTjView.top) {
                if (tabIndex != 3) {
                    tabIndex = 3
                    topGoodsTop.setOnItemSelect(3)
                }
            }
            scrollviewFlag = false
        })

        topGoodsTop.setOnItemSelectListener(object : GoodDetailTopTitle.OnTabSelectListener {
            override fun onItemSelect(position: Int) {
                if (!scrollviewFlag) {
                    when (position) {
                        0 -> scrollView.scrollTo(0, goodsInfoView.top)
                        1 -> scrollView.scrollTo(0, shopInfoView.top)
                        2 -> scrollView.scrollTo(0, goodsDetailView.top)
                        3 -> scrollView.scrollTo(0, goodsTjView.top)
                    }
                }
                //用户点击tablayout时，标记不是scrollview主动滑动
                scrollviewFlag = false
            }
        })
    }


    /**
     * 仿淘宝商品详情第一个是视频
     * @return
     */
    private fun getTestDataVideo(): List<GoodDetailTopBarBean>? {
        val list: MutableList<GoodDetailTopBarBean> = ArrayList<GoodDetailTopBarBean>()
        list.add(GoodDetailTopBarBean(imageUrl = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2534506313,1688529724&fm=26&gp=0.jpg", viewType = 1))
        list.add(GoodDetailTopBarBean(imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586443031238&di=8a0c83dfd33916a3044c28fcd3685f29&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F21%2F09%2F01200000026352136359091694357.jpg", viewType = 1))
        list.add(GoodDetailTopBarBean(imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586443031237&di=1e7d85648997dbc9c6045a502d9c5e24&imgtype=0&src=http%3A%2F%2Fbbs.jooyoo.net%2Fattachment%2FMon_0905%2F24_65548_2835f8eaa933ff6.jpg", viewType = 1))
        list.add(GoodDetailTopBarBean(imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586443031233&di=f6ad3d53ef63e76de9905859d1d4b865&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fspider202048%2F29%2Fw1093h536%2F20200408%2F05a4-iryninw6738585.png", viewType = 1))
        list.add(GoodDetailTopBarBean(imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586443031231&di=3e78616ada111050b3763da6387aab9a&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F16%2F18%2F300000932954129238184537620_950.jpg", viewType = 1))
        return list
    }

    /**
     * 初始化数据
     */
    override fun initData() {
        mBinding.vm?.loadGoodsInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        banner = null
        bannerAdapter = null
    }
}
