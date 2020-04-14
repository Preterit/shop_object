package com.shangyi.kt.ui.goods.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shangyi.business.R
import com.shangyi.business.weight.GoodsDetailTjItemView
import com.shangyi.kt.ui.goods.bean.ReecommendGood
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
        if (data == null) return
        val list = ArrayList<GoodsDetailTjItemView?>()
        list.add(holder?.itemView?.findViewById<GoodsDetailTjItemView>(R.id.goodsDetailTj0))
        list.add(holder?.itemView?.findViewById<GoodsDetailTjItemView>(R.id.goodsDetailTj1))
        list.add(holder?.itemView?.findViewById<GoodsDetailTjItemView>(R.id.goodsDetailTj2))
        list.add(holder?.itemView?.findViewById<GoodsDetailTjItemView>(R.id.goodsDetailTj3))
        list.add(holder?.itemView?.findViewById<GoodsDetailTjItemView>(R.id.goodsDetailTj4))
        list.add(holder?.itemView?.findViewById<GoodsDetailTjItemView>(R.id.goodsDetailTj5))
        for ((index, item) in data.data.withIndex()) {
            if (index == 6) return
            isShow(item, list[index])
        }
    }

    override fun setDatas(datas: MutableList<GoodsDetailTjBean>?) {
        super.setDatas(datas)
        notifyDataSetChanged()
    }

    fun isShow(data: ReecommendGood?, view: GoodsDetailTjItemView?) {
        view?.visibility = if (data == null) {
            View.GONE
        } else {
            view?.setData(data)
            View.VISIBLE
        }
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
        val data: ArrayList<ReecommendGood?>
)



