package com.shangyi.kt.ui.goods.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shangyi.business.R
import com.shangyi.business.weight.GoodsDetailTjItemView
import com.shangyi.kt.ui.goods.GoodsDetailActivity
import com.shangyi.kt.ui.goods.bean.ReecommendGood
import com.youth.banner.adapter.BannerAdapter
import kotlinx.android.synthetic.main.item_goodsdetail_tuijian_test.view.*

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

        holder?.itemView?.goodsDetailTj0?.setOnClickListener {
            val intent = Intent(it.context, GoodsDetailActivity::class.java)
            intent.putExtra("goodsId",  data.data[0]?.id)
            it.context.startActivity(intent)
        }
        holder?.itemView?.goodsDetailTj1?.setOnClickListener {
            val intent = Intent(it.context, GoodsDetailActivity::class.java)
            intent.putExtra("goodsId",  data.data[1]?.id)
            it.context.startActivity(intent)
        }
        holder?.itemView?.goodsDetailTj2?.setOnClickListener {
            val intent = Intent(it.context, GoodsDetailActivity::class.java)
            intent.putExtra("goodsId",  data.data[2]?.id)
            it.context.startActivity(intent)
        }
        holder?.itemView?.goodsDetailTj3?.setOnClickListener {
            val intent = Intent(it.context, GoodsDetailActivity::class.java)
            intent.putExtra("goodsId",  data.data[3]?.id)
            it.context.startActivity(intent)
        }
        holder?.itemView?.goodsDetailTj4?.setOnClickListener {
            val intent = Intent(it.context, GoodsDetailActivity::class.java)
            intent.putExtra("goodsId",  data.data[4]?.id)
            it.context.startActivity(intent)
        }
        holder?.itemView?.goodsDetailTj5?.setOnClickListener {
            val intent = Intent(it.context, GoodsDetailActivity::class.java)
            intent.putExtra("goodsId",  data.data[5]?.id)
            it.context.startActivity(intent)
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



