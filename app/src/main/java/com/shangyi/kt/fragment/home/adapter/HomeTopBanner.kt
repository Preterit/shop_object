package com.shangyi.kt.fragment.home.adapter

import android.content.Intent
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.study.glidemodel.GlideImageView
import com.study.glidemodel.R
import com.youth.banner.adapter.BannerAdapter


/**
 * Date:2020/4/9
 * author:lwb
 * Desc:
 */
class HomeTopBanner : BannerAdapter<HomeBannerBean, HomeTopBannerHoler>(arrayListOf()) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): HomeTopBannerHoler {
        val imageView = GlideImageView(parent!!.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        return HomeTopBannerHoler(imageView)
    }

    override fun onBindView(holder: HomeTopBannerHoler?, data: HomeBannerBean?, position: Int, size: Int) {
        holder?.imageView?.loadImage(data?.img, R.color.placeholder_color)
//        holder?.imageView?.context?.startActivity(Intent( holder?.imageView?.context,))
    }

    override fun setDatas(datas: MutableList<HomeBannerBean>?) {
        super.setDatas(datas)
        notifyDataSetChanged()
    }
}

/**
 * 定义的banner hodler
 */
class HomeTopBannerHoler : RecyclerView.ViewHolder {

    var imageView: GlideImageView? = null

    constructor(itemView: GlideImageView) : super(itemView) {
        this.imageView = itemView
    }
}


data class HomeBannerBean(
        val img: String?
)