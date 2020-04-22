package com.shangyi.kt.ui.pingjia

import android.util.Log
import android.view.View
import androidx.core.view.get
import com.sdxxtop.base.BaseKTActivity
import com.shangyi.business.R
import com.shangyi.business.databinding.ActivityAddPinglunBinding
import com.shangyi.kt.ui.pingjia.model.PingjiaModel
import kotlinx.android.synthetic.main.activity_add_pinglun.*

class AddPinglunActivity : BaseKTActivity<ActivityAddPinglunBinding, PingjiaModel>() {
    override fun layoutId() = R.layout.activity_add_pinglun
    override fun vmClazz() = PingjiaModel::class.java
    override fun bindVM() {
        mBinding.vm = mViewModel
        mBinding.activity = this
    }

    private var starNum1 = 0f
    private var starNum2 = 0f
    private var starNum3 = 0f
    private var starNum4 = 0f
    private var starNum5 = 0f

    override fun initObserve() {

    }

    override fun initView() {
        bar1.setOnRatingChangeListener { bar, RatingCount -> starNum1 = RatingCount }
        bar2.setOnRatingChangeListener { bar, RatingCount -> starNum2 = RatingCount }
        bar3.setOnRatingChangeListener { bar, RatingCount -> starNum3 = RatingCount }
        bar4.setOnRatingChangeListener { bar, RatingCount -> starNum4 = RatingCount }
        bar5.setOnRatingChangeListener { bar, RatingCount -> starNum5 = RatingCount }
        glideImageView.loadImage("", R.color.red)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tvAdd -> {
                // 保存
                addPingjia()
            }
        }
    }

    /**
     * 添加评论
     */
    private fun addPingjia() {
        val content = edit.text.toString().trim()

        Log.e("保存 ---- ", "$starNum1 , $starNum2 , $starNum3 , $starNum4 , $starNum5")
    }
}
