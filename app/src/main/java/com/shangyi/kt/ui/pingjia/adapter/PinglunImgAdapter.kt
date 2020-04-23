package com.shangyi.kt.ui.pingjia.adapter

import androidx.appcompat.app.AppCompatActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.entity.LocalMedia
import com.shangyi.business.R
import com.shangyi.kt.ui.pingjia.bean.CommentImg
import com.shangyi.kt.ui.pingjia.weight.GlideEngine
import kotlinx.android.synthetic.main.item_pinglun_img.view.*

/**
 * Date:2020/4/21
 * author:lwb
 * Desc:
 */
class PinglunImgAdapter : BaseQuickAdapter<CommentImg?, BaseViewHolder> {

    val imgData = arrayListOf<LocalMedia>()

    constructor(data: ArrayList<CommentImg?>?) : super(R.layout.item_pinglun_img, data) {
        imgData.clear()
        data?.forEach {
            imgData.add(LocalMedia(it?.url ?: "", 0, 0, ""))
        }
    }

    override fun convert(holder: BaseViewHolder, item: CommentImg?) {
        holder.itemView.glideImageView.loadImage(item?.url ?: "", R.color.placeholder_color)


        holder.itemView.setOnClickListener {
            PictureSelector.create(context as AppCompatActivity)
                    .themeStyle(R.style.picture_default_style)
                    .isNotPreviewDownload(true)
                    .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                    .openExternalPreview(holder.layoutPosition, imgData)
        }
    }

}