package com.shangyi.kt.ui.goods

import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityGoodsDetailBinding
import com.shangyi.kt.ui.goods.adapter.MultipleTypesAdapter
import com.shangyi.kt.ui.goods.bean.GoodDetailTopBarBean
import com.shangyi.kt.ui.goods.model.GoodDetailModel
import com.shangyi.kt.ui.goods.weight.banner.indicator.NumIndicator
import com.youth.banner.Banner
import com.youth.banner.config.IndicatorConfig
import java.util.*

class GoodsDetailActivity : BaseKTActivity<ActivityGoodsDetailBinding, GoodDetailModel>() {

    override fun vmClazz() = GoodDetailModel::class.java
    override fun layoutId() = R.layout.activity_goods_detail
    override fun bindVM() {
        mBinding.vm = mViewModel
    }

    private var bannerAdapter: MultipleTypesAdapter? = null   // 轮播图适配器
    private var banner: Banner<GoodDetailTopBarBean, MultipleTypesAdapter>? = null

    override fun initObserve() {

    }


    override fun initView() {
        bannerAdapter = MultipleTypesAdapter(this, getTestDataVideo())
        banner = findViewById(R.id.banner)
        banner!!.setAdapter(bannerAdapter!!)
                .setIndicator(NumIndicator(this))
                .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                .start()
    }


    /**
     * 仿淘宝商品详情第一个是视频
     * @return
     */
    fun getTestDataVideo(): List<GoodDetailTopBarBean>? {
        val list: MutableList<GoodDetailTopBarBean> = ArrayList<GoodDetailTopBarBean>()
        list.add(GoodDetailTopBarBean(imageUrl = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2534506313,1688529724&fm=26&gp=0.jpg", viewType = 1))
        list.add(GoodDetailTopBarBean(imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586443031238&di=8a0c83dfd33916a3044c28fcd3685f29&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F21%2F09%2F01200000026352136359091694357.jpg", viewType = 1))
        list.add(GoodDetailTopBarBean(imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586443031237&di=1e7d85648997dbc9c6045a502d9c5e24&imgtype=0&src=http%3A%2F%2Fbbs.jooyoo.net%2Fattachment%2FMon_0905%2F24_65548_2835f8eaa933ff6.jpg", viewType = 1))
        list.add(GoodDetailTopBarBean(imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586443031233&di=f6ad3d53ef63e76de9905859d1d4b865&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fspider202048%2F29%2Fw1093h536%2F20200408%2F05a4-iryninw6738585.png", viewType = 1))
        list.add(GoodDetailTopBarBean(imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586443031231&di=3e78616ada111050b3763da6387aab9a&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F16%2F18%2F300000932954129238184537620_950.jpg", viewType = 1))
        return list
    }

    override fun onDestroy() {
        super.onDestroy()
        banner = null
        bannerAdapter = null
    }
}
