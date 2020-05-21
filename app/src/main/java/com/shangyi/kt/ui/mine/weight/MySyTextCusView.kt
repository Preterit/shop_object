package com.shangyi.kt.ui.mine.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.shangyi.business.R
import com.shangyi.kt.ui.mine.bean.ChildItemBean
import kotlinx.android.synthetic.main.custom_mysy_text.view.*

/**
 * Date:2020/5/11
 * author:lwb
 * Desc:
 */
class MySyTextCusView constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_mysy_text, this, true)
    }

    fun setData(data: ChildItemBean?, isShowBottomLine: Boolean) {
        if (data == null) return
        tvLeftText.text = "订单号：${data.order_num}"
        tvRightText.text = "收入：¥${data.amount}"
        tvLine.visibility = if (isShowBottomLine) View.VISIBLE else View.GONE
    }

}