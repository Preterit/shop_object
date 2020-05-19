package com.shangyi.kt.ui.mine.weight.imgselect

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.shangyi.business.R
import com.shangyi.kt.ui.pingjia.weight.GlideEngine
import kotlinx.android.synthetic.main.cus_media_layout_view.view.*
import java.io.File

/**
 * Date:2020/5/18
 * author:lwb
 * Desc:
 */
class CusMediaSelect : LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private var adapter = ImgSelectAdapter()   // 图片选择的适配器
    private var selectImgList: ArrayList<LocalMedia> = ArrayList()   // 选中的图片数据
    private var selectVideoList: ArrayList<LocalMedia> = ArrayList()  // 选中的视屏数据
    private val allDataList: ArrayList<LocalMedia> = ArrayList()      // 选中的媒体文件总集合

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.cus_media_layout_view, this, true)

        recyclerview.layoutManager = GridLayoutManager(context, 4)
        recyclerview.adapter = adapter

        adapter.setOnImgItemClick(object : ImgSelectAdapter.OnImgItemClick {
            override fun onAddClick(selectNum: Int) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(context as Activity)
                        .openGallery(PictureMimeType.ofImage()) // 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .maxSelectNum(selectNum) // 最大图片选择数量
                        .imageSpanCount(4) // 每行显示个数
                        .selectionMode(PictureConfig.MULTIPLE) // 多选 or 单选
                        .previewImage(true) // 是否可预览图片
                        .isCamera(true) // 是否显示拍照按钮
                        .isZoomAnim(true) // 图片列表点击 缩放效果 默认true
                        .synOrAsy(true) //同步true或异步false 压缩 默认同步
                        .minimumCompressSize(100) // 小于100kb的图片不压缩
//                        .selectionMedia(selectImgList) // 是否传入已选图片
                        .recordVideoSecond(30)
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST) //结果回调onActivityResult code

            }

            override fun onLookClick(position: Int, list: ArrayList<LocalMedia>) {
                PictureSelector.create(context as Activity)
                        .themeStyle(R.style.picture_default_style)
                        .isNotPreviewDownload(true)
                        .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                        .openExternalPreview(position, list)
            }
        })
    }

    fun callActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    // 图片选择结果回调
                    val localMedia = PictureSelector.obtainMultipleResult(data) as ArrayList<LocalMedia>
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    if (localMedia.size == 1) {
                        if (localMedia[0].chooseModel == PictureConfig.TYPE_VIDEO) {
                            selectVideoList = localMedia
                        } else {
                            selectImgList = localMedia
                        }
                    } else {
                        selectImgList = localMedia
                    }

//                    for (LocalMedia media : localMedia) {
//                        Log.i("图片-----》", media.getPath());
//                    }
                    allDataList.clear()
                    allDataList.addAll(selectVideoList)
                    allDataList.addAll(selectImgList)

                    refreshAdapter(allDataList)
                }
            }
        }
    }

    /**
     * 刷新适配器
     */
    private fun refreshAdapter(list: ArrayList<LocalMedia>) {
        val listResult = ArrayList<QuickMultipleEntity>()
        list.forEach {
            listResult.add(QuickMultipleEntity(it.compressPath ?: it.path
            ?: it.cutPath, QuickMultipleEntity.CENTER_FLAG))
        }
        adapter.setList(listResult)
    }

    /**
     * 获取图片路径
     */
    fun getImagePushPath(): List<File>? {
        //设置相片
        val imgList: MutableList<File> = ArrayList()
        val data: List<QuickMultipleEntity> = adapter.data
        if (data.isNotEmpty()) {
            for (i in data.indices) {
                var item = data[i]
                if (item.itemType == QuickMultipleEntity.CENTER_FLAG) {
                    var path = item.path
                    if (!TextUtils.isEmpty(path)) {
                        imgList.add(File(path))
                    }
                }
            }
        }
        return imgList
    }
}