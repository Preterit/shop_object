package com.shangyi.business.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.shangyi.business.R

/**
 * Date:2020/4/10
 * author:lwb
 * Desc:
 */
class GoodsDetailTjItemView : LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val view = LayoutInflater.from(context).inflate(R.layout.item_goodsdetail_tuijian, this, true)
    }

    fun setData(){

    }
}