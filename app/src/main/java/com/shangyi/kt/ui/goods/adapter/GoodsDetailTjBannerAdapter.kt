package com.shangyi.kt.ui.goods.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shangyi.business.R
import com.youth.banner.adapter.BannerAdapter

/**
 * Date:2020/4/10
 * author:lwb
 * Desc:
 */
class GoodsDetailTjBannerAdapter : BannerAdapter<GoodsDetailTjBean, GoodsDetailTjBannerHoler>(arrayListOf()) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): GoodsDetailTjBannerHoler {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_goodsdetail_tuijian_test, parent, false)
        return GoodsDetailTjBannerHoler(view)
    }

    override fun onBindView(holder: GoodsDetailTjBannerHoler?, data: GoodsDetailTjBean?, position: Int, size: Int) {
//        holder?.recyclerView?.recyclerview = GridLayoutManager(holder?.recyclerView?.context, 3)
//        holder?.recyclerView?.adapter = GoodsDetailLookmoreAdapter(R.layout.item_goodsdetail_tuijian)
    }
}

/**
 * 定义的banner hodler
 */
class GoodsDetailTjBannerHoler : RecyclerView.ViewHolder {

    var recyclerView: View? = null

    constructor(itemView: View) : super(itemView) {
        this.recyclerView = itemView
    }
}


data class GoodsDetailTjBean(
        val img: String?

)


