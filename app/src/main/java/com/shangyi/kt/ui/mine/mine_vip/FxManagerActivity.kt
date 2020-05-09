package com.shangyi.kt.ui.mine.mine_vip

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.appbar.AppBarLayout
import com.shangyi.business.R
import com.shangyi.kt.fragment.categroy.adapter.BannerDataBean
import com.shangyi.kt.fragment.categroy.adapter.CategroyRightBanner
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_fx_manager.*
import kotlin.math.abs

class FxManagerActivity : AppCompatActivity() {

    private var adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fx_manager)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        initView()
    }

    private var banner: Banner<BannerDataBean, CategroyRightBanner>? = null  // 轮播图banner
    private var bannerAdapter = CategroyRightBanner()   // 轮播图适配器

    private fun initView() {
        val mToolbar = findViewById<Toolbar>(R.id.topSearchLayout)
        val mAppBarLayout = findViewById<AppBarLayout>(R.id.AppFragment_AppBarLayout);
        mAppBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            mToolbar.setBackgroundColor(changeAlpha(resources.getColor(R.color.red), abs(verticalOffset * 1.0f) / appBarLayout.totalScrollRange))
        })

        val topView = LayoutInflater.from(this).inflate(R.layout.item_home_top_view, null, false)
        adapter.addHeaderView(topView)

        banner = findViewById(R.id.banner)
        if (banner != null && bannerAdapter != null) {
            bannerAdapter?.setDatas(arrayListOf(
                    BannerDataBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588867415798&di=4ba8ba3cfe320c60bf81b91f798523e0&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg"),
                    BannerDataBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588867415797&di=b9b1851d4f679a963d694d2704878990&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F64%2F76%2F20300001349415131407760417677.jpg"),
                    BannerDataBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588867415797&di=2f2605081af02771526848c0800fb64b&imgtype=0&src=http%3A%2F%2Ft7.baidu.com%2Fit%2Fu%3D888206991%2C1760071208%26fm%3D191%26app%3D48%26wm%3D1%2C13%2C90%2C45%2C0%2C7%26wmo%3D10%2C10%26n%3D0%26g%3D0n%26f%3DJPEG%3Fsec%3D1853310920%26t%3D5737bdb8b0a6dccf99f9e8ae009bb4fc")
            ))
            banner!!.setAdapter(bannerAdapter!!).setIndicator(CircleIndicator(this)).start()
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

    class Adapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_car_goods_view) {
        init {
            setList(arrayListOf("", "", "", "", "", "", "", ""))
        }

        override fun convert(holder: BaseViewHolder, item: String) {

        }
    }
}
