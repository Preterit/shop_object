package com.shangyi.kt.ui.goods.weight

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.shangyi.business.R
import com.shangyi.kt.ui.goods.GoodsDetailActivity
import kotlinx.android.synthetic.main.good_detail_toptitle.view.*

/**
 * Date:2020/4/9
 * author:lwb
 * Desc:
 */
class GoodDetailTopTitle : FrameLayout, View.OnClickListener {

    private var mListener: OnTabSelectListener? = null
    private var isCollect = false // 是否收藏

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        clipToPadding = true
        fitsSystemWindows = true

        LayoutInflater.from(context).inflate(R.layout.good_detail_toptitle, this, true)

        tvProduct.isSelected = true
        setTextBold(tvProduct)

        tvProduct.setOnClickListener(this)
        tvPinjia.setOnClickListener(this)
        tvDetail.setOnClickListener(this)
        tvTuijian.setOnClickListener(this)
        ivMore.setOnClickListener {
            if (mListener != null) {
                isCollect = !isCollect
                mListener?.clooectClick(isCollect)
            }
        }
        ivBack.setOnClickListener {
            if (context is GoodsDetailActivity) {
                val activity = context as GoodsDetailActivity
                activity.finish()
            }
        }
    }

    /**
     * 设置字体加粗
     */
    fun setTextBold(tx: TextView) {
        tx.paint.isFakeBoldText = tx.isSelected
    }

    override fun onClick(v: View?) {
        tvProduct.isSelected = false
        tvPinjia.isSelected = false
        tvDetail.isSelected = false
        tvTuijian.isSelected = false
        when (v?.id) {
            R.id.tvProduct -> {
                tvProduct.isSelected = true
                mListener?.onItemSelect(0)
            }
            R.id.tvPinjia -> {
                tvPinjia.isSelected = true
                mListener?.onItemSelect(1)
            }
            R.id.tvDetail -> {
                tvDetail.isSelected = true
                mListener?.onItemSelect(2)
            }
            R.id.tvTuijian -> {
                tvTuijian.isSelected = true
                mListener?.onItemSelect(3)
            }
        }
        setTextBold(tvProduct)
        setTextBold(tvPinjia)
        setTextBold(tvDetail)
        setTextBold(tvTuijian)
    }

    /**
     * 设置滑动选中的条目
     */
    fun setOnItemSelect(position: Int) {
        tvProduct.isSelected = false
        tvPinjia.isSelected = false
        tvDetail.isSelected = false
        tvTuijian.isSelected = false
        when (position) {
            0 -> {
                tvProduct.isSelected = true
            }
            1 -> {
                tvPinjia.isSelected = true
            }
            2 -> {
                tvDetail.isSelected = true
            }
            3 -> {
                tvTuijian.isSelected = true
            }
        }
        setTextBold(tvProduct)
        setTextBold(tvPinjia)
        setTextBold(tvDetail)
        setTextBold(tvTuijian)
    }

    fun setCollectSuccess(isCollect: Boolean) {
        this.isCollect = isCollect
        if (isCollect) {
            ivMore.setImageResource(R.drawable.icon_goodsdetail_collect)
        } else {
            ivMore.setImageResource(R.drawable.icon_goodsdetail_uncollect)
        }
    }

    fun setOnItemSelectListener(listener: OnTabSelectListener) {
        this.mListener = listener
    }

    interface OnTabSelectListener {
        fun onItemSelect(position: Int)
        fun clooectClick(isCollect: Boolean)
    }

}