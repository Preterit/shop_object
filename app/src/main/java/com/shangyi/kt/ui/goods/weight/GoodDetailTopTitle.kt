package com.shangyi.kt.ui.goods.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.shangyi.business.R
import com.shangyi.kt.ui.goods.GoodDetailActivity
import kotlinx.android.synthetic.main.good_detail_toptitle.view.*

/**
 * Date:2020/4/9
 * author:lwb
 * Desc:
 */
class GoodDetailTopTitle : FrameLayout, View.OnClickListener {


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
        ivBack.setOnClickListener {
            if (context is GoodDetailActivity) {
                val activity = context as GoodDetailActivity
                activity.finish()
            }
        }
    }

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
            }
            R.id.tvPinjia -> {
                tvPinjia.isSelected = true
            }
            R.id.tvDetail -> {
                tvDetail.isSelected = true
            }
            R.id.tvTuijian -> {
                tvTuijian.isSelected = true
            }
        }
        setTextBold(tvProduct)
        setTextBold(tvPinjia)
        setTextBold(tvDetail)
        setTextBold(tvTuijian)
    }
}