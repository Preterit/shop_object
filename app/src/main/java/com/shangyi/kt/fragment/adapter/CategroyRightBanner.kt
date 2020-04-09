package com.shangyi.kt.fragment.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.youth.banner.adapter.BannerAdapter


/**
 * Date:2020/4/9
 * author:lwb
 * Desc:
 */
class CategroyRightBanner : BannerAdapter<BannerDataBean, CategroyRightBannerHoler>(arrayListOf()) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): CategroyRightBannerHoler {
        val imageView = ImageView(parent!!.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        return CategroyRightBannerHoler(imageView)
    }

    override fun onBindView(holder: CategroyRightBannerHoler?, data: BannerDataBean?, position: Int, size: Int) {
        Glide.with(holder?.itemView?.context!!).load(data?.img).into(holder.imageView!!)
    }

    override fun setDatas(datas: MutableList<BannerDataBean>?) {
        super.setDatas(datas)
        notifyDataSetChanged()
    }
}

/**
 * 定义的banner hodler
 */
class CategroyRightBannerHoler : RecyclerView.ViewHolder {

    var imageView: ImageView? = null

    constructor(itemView: ImageView) : super(itemView) {
        this.imageView = itemView
    }
}



data class BannerDataBean(
        val img: String?
)