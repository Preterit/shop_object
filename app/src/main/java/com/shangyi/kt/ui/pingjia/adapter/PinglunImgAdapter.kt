package com.shangyi.kt.ui.pingjia.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.pingjia.bean.CommentImg
import kotlinx.android.synthetic.main.item_pinglun_img.view.*

/**
 * Date:2020/4/21
 * author:lwb
 * Desc:
 */
class PinglunImgAdapter constructor(data: ArrayList<CommentImg?>?) :
        BaseQuickAdapter<CommentImg?, BaseViewHolder>(R.layout.item_pinglun_img, data) {

    override fun convert(holder: BaseViewHolder, item: CommentImg?) {
        holder.itemView.glideImageView.loadImage(item?.url ?: "", R.color.placeholder_color)
    }

}