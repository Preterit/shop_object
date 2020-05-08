package com.shangyi.kt.ui.goods

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdxxtop.base.BaseKTActivity
import com.sdxxtop.base.utils.UIUtils
import com.sdxxtop.webview.remotewebview.BaseWebView
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityGoodsDetailBinding
import com.shangyi.business.databinding.ItemGoodsDetailGoodsinfoBinding
import com.shangyi.business.weight.dialog.GoodsYhqDialog
import com.shangyi.kt.fragment.car.entity.CommitOrderBean
import com.shangyi.kt.fragment.car.entity.GoodsInfoBean
import com.shangyi.kt.ui.address.AddressListActivity
import com.shangyi.kt.ui.address.bean.AreaListBean
import com.shangyi.kt.ui.goods.adapter.*
import com.shangyi.kt.ui.goods.bean.GoodDetailTopBarBean
import com.shangyi.kt.ui.goods.bean.GoodsDetailBean
import com.shangyi.kt.ui.goods.bean.ReecommendGood
import com.shangyi.kt.ui.goods.bean.YouhuiquanBean
import com.shangyi.kt.ui.goods.model.GoodDetailModel
import com.shangyi.kt.ui.goods.weight.GoodDetailTopTitle
import com.shangyi.kt.ui.goods.weight.ProductSkuDialog
import com.shangyi.kt.ui.goods.weight.banner.indicator.NumIndicator
import com.shangyi.kt.ui.order.AffirmOrderActivity
import com.shangyi.kt.ui.pingjia.PingjiaActivity
import com.youth.banner.Banner
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_goods_detail.*
import kotlinx.android.synthetic.main.item_goods_detail_goodsdetail.view.*
import kotlinx.android.synthetic.main.item_goods_detail_goodsinfo.view.*
import kotlinx.android.synthetic.main.item_goods_detail_shopinfo.view.*
import kotlinx.android.synthetic.main.item_goods_detail_tuijian.view.*
import java.util.concurrent.ConcurrentHashMap

class GoodsDetailActivity : BaseKTActivity<ActivityGoodsDetailBinding, GoodDetailModel>() {

    override fun vmClazz() = GoodDetailModel::class.java
    override fun layoutId() = R.layout.activity_goods_detail
    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    private var bannerAdapter: MultipleTypesAdapter? = null   // 轮播图适配器
    private var goodsDetailTjBannerAdapter = GoodsDetailTjBannerAdapter()   // 商铺推荐的轮播图适配器
    private var banner: Banner<GoodDetailTopBarBean, MultipleTypesAdapter>? = null  // 商品轮播图
    private val topBannerDataList: MutableList<GoodDetailTopBarBean> = ArrayList<GoodDetailTopBarBean>()  // 商品顶部轮播图
    private var shopTjBanner: Banner<GoodDetailTopBarBean, GoodsDetailTjBannerAdapter>? = null  // 商品推荐轮播图

    private var viewList = ConcurrentHashMap<Int, View?>()  // 填充的view布局
    private var bindList = ArrayList<ViewDataBinding?>()  // 填充的DataBind

    private var pjAdapter = PjAdaper()  // 商品评价的信息适配器
    private var attriAdapter = AttriAdapter(Color.parseColor("#ffffff"), Color.parseColor("#F8F8F8"))  // 商品详情属性适配器
    private var lookMoreAdapter = GoodsDetailLookmoreAdapter()  // 推荐查看更多适配器

    private var webView: BaseWebView? = null  // 商品详情的h5页面
    private var scrollviewFlag = false  // 滑动的标识
    private var tabIndex = -1  // 当前选中tab的下标
    private var goodsId = 0 // 商品ID
    private var addressId = 0 // 地址ID
    private var skuId = "0" // 商品规格ID
    private var number = 1 // 商品数量
    private var dialog: ProductSkuDialog? = null   // 规格弹框
    private var carSelect = false   // 是否添加到购物车
    private var yhqDataList: List<YouhuiquanBean?>? = null // 优惠券数据列表

    /**
     * 购买跳转传参
     */
    private var goodBean: GoodsInfoBean? = null  // 商品信息
    private var orderInfo: CommitOrderBean? = null  // 商品信息


    override fun initObserve() {
        mBinding.vm?.data?.observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                bindData(it)
            }
        })

        mBinding.vm?.product?.observe(this, Observer {
            if (dialog == null) {
                dialog = ProductSkuDialog(this)
                dialog?.setData(it, ProductSkuDialog.Callback { sku, quantity ->
                    dialog?.dismiss()
                    skuId = sku.id
                    number = quantity
                    viewList[0]?.tvStandard?.text = "${sku.attributes.toString().replace("[", "").replace("]", "")} $quantity 部"
                    // 切换规格， 切换商品图片
                    goodBean?.goodsImg = sku.mainImage
                    goodBean?.goodsSpecId = skuId.toInt()
                    goodBean?.SpecStr = "${sku.attributes.toString().replace("[", "").replace("]", "")} $quantity 部"
                    goodBean?.goodsPrice = sku.sellingPrice

                    if (carSelect) {
                        carSelect = false
                        mBinding.vm?.addCar(goodsId, skuId, number)
                    }
                })
            }
        })
    }

    override fun initView() {
        val view = LayoutInflater.from(this).inflate(R.layout.item_goods_detail_goodsdetail, null, false)   // 商品详情
        viewList[2] = view
        webView = view.goodsDetailWeb
        view.attrRecyclerview.layoutManager = LinearLayoutManager(this@GoodsDetailActivity)
        view.attrRecyclerview.adapter = attriAdapter
        addChildView()

        /**
         * 商品信息初始化
         */
        AsyncLayoutInflater(this).inflate(R.layout.item_goods_detail_goodsinfo, null) { view, resid, parent ->
            viewList[0] = view

            val bind = DataBindingUtil.bind<ItemGoodsDetailGoodsinfoBinding>(view)
            bind?.lifecycleOwner = this@GoodsDetailActivity
            bind?.vm = mViewModel
            bind?.activity = this@GoodsDetailActivity
            bindList.add(bind)

            addChildView()

            //原价 字体画线
            view?.tvInvalidate.paint.isAntiAlias = true
            view?.tvInvalidate.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            banner = view?.findViewById(R.id.banner)
            bannerAdapter = MultipleTypesAdapter(this@GoodsDetailActivity, arrayListOf<GoodDetailTopBarBean>())
            banner!!.setAdapter(bannerAdapter!!)
                    .setIndicator(NumIndicator(this@GoodsDetailActivity))
                    .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
        }


        /**
         * 店铺相关初始化
         */
        AsyncLayoutInflater(this).inflate(R.layout.item_goods_detail_shopinfo, null) { view, resid, parent ->
            viewList[1] = view

            shopTjBanner = view?.findViewById(R.id.shopTjBanner)
            view?.findViewById<TextView>(R.id.tvPjMore).setOnClickListener {
                var intent = Intent(this@GoodsDetailActivity, PingjiaActivity::class.java)
                intent.putExtra("goodId", goodsId)
                startActivity(intent)
            }
            shopTjBanner!!.setAdapter(goodsDetailTjBannerAdapter)
                    .setIndicator(CircleIndicator(this@GoodsDetailActivity))
                    .setIndicatorGravity(IndicatorConfig.Direction.CENTER)


            view.pjRecyclerView.layoutManager = LinearLayoutManager(this@GoodsDetailActivity)
            view.pjRecyclerView.adapter = pjAdapter

            addChildView()
        }

        /**
         * 商品推荐
         */
        AsyncLayoutInflater(this).inflate(R.layout.item_goods_detail_tuijian, null) { view, resid, parent ->
            viewList[3] = view
            view?.goodsDetailLookmoreRecycler?.layoutManager = GridLayoutManager(this@GoodsDetailActivity, 2)
            view?.goodsDetailLookmoreRecycler?.adapter = lookMoreAdapter
            addChildView()
        }

        //scrollview滑动事件监听
        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (viewList.size != 4) {
                return@OnScrollChangeListener
            }
            scrollviewFlag = true
            if (scrollY < viewList[1]!!.top) {
                if (tabIndex != 0) { //增加判断，如果滑动的区域是tableIndex=0对应的区域，则不改变tablayout的状态
                    tabIndex = 0
                    topGoodsTop.setOnItemSelect(0)
                }
            } else if (scrollY >= viewList[1]!!.top && scrollY < viewList[2]!!.top) {
                if (tabIndex != 1) {
                    tabIndex = 1
                    topGoodsTop.setOnItemSelect(1)
                }
            } else if (scrollY >= viewList[2]!!.top && scrollY < viewList[3]!!.top) {
                if (tabIndex != 2) {
                    tabIndex = 2
                    topGoodsTop.setOnItemSelect(2)
                }
            } else if (scrollY >= viewList[3]!!.top) {
                if (tabIndex != 3) {
                    tabIndex = 3
                    topGoodsTop.setOnItemSelect(3)
                }
            }
            scrollviewFlag = false
        })

        topGoodsTop.setOnItemSelectListener(object : GoodDetailTopTitle.OnTabSelectListener {
            override fun onItemSelect(position: Int) {
                if (viewList.size != 4) {
                    return
                }
                if (!scrollviewFlag) {
                    when (position) {
                        0 -> scrollView.scrollTo(0, viewList[0]!!.top)
                        1 -> scrollView.scrollTo(0, viewList[1]!!.top)
                        2 -> scrollView.scrollTo(0, viewList[2]!!.top)
                        3 -> scrollView.scrollTo(0, viewList[3]!!.top)
                    }
                }
                //用户点击tablayout时，标记不是scrollview主动滑动
                scrollviewFlag = false
            }

            override fun clooectClick() {
                mBinding.vm?.collectGoods(goodsId)
            }
        })
    }

    /**
     * 添加子view
     */
    private fun addChildView() {
        if (viewList.size == 4) {
            detailLayout.addView(viewList[0])
            detailLayout.addView(viewList[1])
            detailLayout.addView(viewList[2])
            detailLayout.addView(viewList[3])
        }
    }

    /**
     * 初始化数据
     */
    override fun initData() {
        goodsId = intent.getIntExtra("goodsId", 0)
        mBinding.vm?.loadGoodsInfo(goodsId)
        mBinding.vm?.loadGoodsSpec(goodsId)
    }

    /**
     * 释放资源
     */
    override fun onDestroy() {
        super.onDestroy()
        banner = null
        bannerAdapter = null

        viewList.forEach {
            it.value == null
        }
        viewList.clear()
        bindList.forEach { it?.unbind() }
    }

    /**
     * 绑定数据
     */
    private fun bindData(it: GoodsDetailBean) {
        yhqDataList = it.discountList
        //商品信息
        setGoodInfoData(it)
        //评论信息
        setShopInfo(it)
        attriAdapter.setList(it.goods_attribute)
        webView?.loadUrl(it.intro)
        lookMoreAdapter.setList(it.reecommendGoods)
        bandBottomData(it)

        goodBean = GoodsInfoBean(
                it.id,
                1,
                it.sale_price,
                it.name,
                it.spec?.value ?: "",
                it.spec?.image,
                it.spec?.product_id ?: 0
        )

        if (goodBean != null) {
            orderInfo = CommitOrderBean(
                    it.shop_id,
                    it.shop_info?.shop_avatar,
                    it.shop_info?.name,
                    arrayListOf(goodBean!!),
                    it.dealer?.cash_back ?: 0f,
                    "",
                    it.sale_price
            )
        }

    }

    /**
     * 底部按钮绑定
     */
    private fun bandBottomData(it: GoodsDetailBean) {
        tvReduceTx.text = "返${it.dealer?.cash_back}"
        tvZhuanMoneyTx.text = "${it.dealer?.dealer}"
    }

    /**
     * 设置商品信息
     */
    private fun setGoodInfoData(it: GoodsDetailBean) {
        //商品轮播图
        if (it.goods_img != null && it.goods_img.isNotEmpty()) {
            topBannerDataList.clear()
            it.goods_img.forEach { topBannerDataList.add(GoodDetailTopBarBean(imageUrl = it.url, viewType = 1)) }
        }
        bannerAdapter?.setDatas(topBannerDataList)
        /******** 优惠券开始 *********/
        val tvLable1 = viewList[0]?.tvLable1
        val tvLable2 = viewList[0]?.tvLable2
        if (it.discountList != null && it.discountList.isNotEmpty()) {
            if (it.discountList[0] != null) {
                var djqStr = mBinding.vm?.getYouhuiquanStr(it.discountList[0]!!)
                if (djqStr.isNullOrEmpty()) tvLable1?.visibility = View.GONE else tvLable1?.visibility = View.VISIBLE
                tvLable1?.text = "$djqStr"
            } else {
                tvLable1?.visibility = View.GONE
            }
            if (it.discountList.size >= 2 && it.discountList[1] != null) {
                var djqStr = mBinding.vm?.getYouhuiquanStr(it.discountList[1]!!)
                if (djqStr.isNullOrEmpty()) tvLable2?.visibility = View.GONE else tvLable2?.visibility = View.VISIBLE
                tvLable2?.text = "$djqStr"
            } else {
                tvLable2?.visibility = View.GONE
            }
            viewList[0]?.tvMoreAction?.visibility = View.VISIBLE
        } else {
            tvLable1?.visibility = View.GONE
            tvLable2?.visibility = View.GONE
        }
        /******** 优惠券结束 *********/


        /******** 规格 *********/
        viewList[0]?.tvStandard?.text = "${mBinding.vm?.getStandardStr(it) ?: "请选择规格"}"
        /******** 收货地址 *********/
        viewList[0]?.tvShippingAddress?.text = "${it.address?.address ?: "请选择收货地址"}"

    }

    /**
     * 设置店铺信息以及评论信息
     */
    private fun setShopInfo(it: GoodsDetailBean) {
        val shopInfoView = viewList[1]
        if (it.comment_count == 0) {
            shopInfoView?.tvProductPjNum?.text = "(${it.comment_count})"
            shopInfoView?.tvPjMore?.text = "好评率0%"
        } else {
            shopInfoView?.tvProductPjNum?.text = "(${it.comment_count})"
            shopInfoView?.tvPjMore?.text = "好评率${it.praise_count / it.comment_count}%"
        }

        /******** 评论列表 *********/
        var appraiseInfo = it.appraiseInfo
        if (appraiseInfo != null && appraiseInfo.isNotEmpty()) {
            pjAdapter.setList(appraiseInfo)
        }
        /******** 店铺 *********/
        shopInfoView?.ivShopPhoto?.loadImage(it.shop_info?.shop_avatar
                ?: "", R.color.placeholder_color)
        shopInfoView?.tvShopName?.text = it.shop_info?.name

        /**
         * 商铺推荐推荐添加数据
         */
        goodsDetailTjBannerAdapter.setDatas(getGoodsDetailTjBannerData(it))
    }

    /**
     * 处理商铺推荐商品banner数据
     */
    private fun getGoodsDetailTjBannerData(it: GoodsDetailBean): ArrayList<GoodsDetailTjBean> {
        var shopRecommend = it.shop_info?.shopRecommend

        var currentItem = 1
        val resultList = ArrayList<GoodsDetailTjBean>()
        var list = arrayListOf<ReecommendGood?>()
        if (shopRecommend != null && shopRecommend.isNotEmpty()) {
            shopRecommend.forEachIndexed { index, item ->
                if (index < currentItem * 6) {
                    list.add(item)
                } else {
                    resultList.add(GoodsDetailTjBean(list))
                    currentItem++
                    list = arrayListOf<ReecommendGood?>()
                    list.add(item)  // 把第7个加进去
                }
            }
            resultList.add(GoodsDetailTjBean(list))
        }
        return resultList
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.standardLayout -> {
                // 规格
                dialog?.show()
            }
            R.id.shAddressLayout -> {
                // 收货地址
                val intent = Intent(this@GoodsDetailActivity, AddressListActivity::class.java)
                startActivityForResult(intent, 11)
            }

            R.id.tvCar -> {
                // 加入购物车
                carSelect = true
                dialog?.show()
            }

            R.id.tvService -> {
                UIUtils.showToast("在线客服")
            }

            R.id.layoutLeft -> {
//                UIUtils.showToast("立即购买")
                buyGoods()
            }

            R.id.layoutRight -> {
                UIUtils.showToast("分享")
            }
            R.id.tvMoreAction -> {
                /******* 优惠券dialog ********/
                if (yhqDataList == null) return
                yuqDialog.show(supportFragmentManager, "")
            }
        }
    }

    /**
     * 购买商品
     */
    private fun buyGoods() {
        val intent = Intent(this, AffirmOrderActivity::class.java)
        intent.putExtra("orderData", arrayListOf(orderInfo))
        startActivity(intent)
    }

    /**
     * 开启activity收到的结果。
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) return
        if (requestCode == 11) {
            val item = data.getParcelableExtra<AreaListBean?>("areaBean")
            addressId = item!!.id
            viewList[0]?.tvShippingAddress?.text = "${item.provice?.name}${item.city?.name}${item.county?.name}${item.detail}"
        }
    }

    /**
     * 优惠券dialog
     */
    private val yuqDialog: GoodsYhqDialog by lazy {
        val dialog = GoodsYhqDialog.newInstance(yhqDataList as java.util.ArrayList<YouhuiquanBean>)
        dialog
    }
}

