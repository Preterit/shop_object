package com.shangyi.kt.ui.mine.weight.imgselect

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.luck.picture.lib.entity.LocalMedia
import com.shangyi.business.R
import kotlinx.android.synthetic.main.item_image_view.view.*
import kotlin.math.abs

/**
 * Date:2020/5/18
 * author:lwb
 * Desc: 多条目类型较少，这里只有两种，用BaseMultiItemQuickAdapter
 */
class ImgSelectAdapter : BaseMultiItemQuickAdapter<QuickMultipleEntity, BaseViewHolder> {

    private var imgData = arrayListOf<QuickMultipleEntity>()
    private var showImgData = arrayListOf<LocalMedia>()
    private var mImgNum: Int

    constructor(imgNum: Int = 3) : super() {
        this.mImgNum = imgNum
        addItemType(QuickMultipleEntity.CHOOSE_FLAG, R.layout.item_media_select)
        addItemType(QuickMultipleEntity.CENTER_FLAG, R.layout.item_image_view)
        setList(arrayListOf())
    }

    override fun convert(holder: BaseViewHolder, item: QuickMultipleEntity) {
        when (holder.itemViewType) {
            QuickMultipleEntity.CHOOSE_FLAG -> {
                holder.itemView.setOnClickListener {
                    mListener?.onAddClick(mImgNum)
                }
            }
            QuickMultipleEntity.CENTER_FLAG -> {
                holder.itemView.imgView.loadImage(item.path)
                holder.itemView.imgDel.setOnClickListener {
                    remove(item)
                    setList(data)
                }
                holder.itemView.imgView.setOnClickListener {
                    mListener?.onLookClick(
                            holder.layoutPosition,
                            showImgData
                    )
                }
            }
        }
    }

    override fun setList(list: Collection<QuickMultipleEntity>?) {
        if (list == null) return
        imgData.clear()
        showImgData.clear()
        list.forEach {
            if (it.itemType == QuickMultipleEntity.CENTER_FLAG) {
                showImgData.add(LocalMedia(it.path, 0, 0, ""))
                imgData.add(it)
            }
        }

        if (imgData.size < mImgNum) {
            imgData.add(QuickMultipleEntity("", QuickMultipleEntity.CHOOSE_FLAG))
        }
        super.setList(imgData)
    }

    interface OnImgItemClick {
        fun onAddClick(maxSize: Int)
        fun onLookClick(position: Int, list: ArrayList<LocalMedia>)
    }

    private var mListener: OnImgItemClick? = null

    fun setOnImgItemClick(listener: OnImgItemClick) {
        this.mListener = listener
    }

}