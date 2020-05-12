package com.shangyi.kt.ui.mine.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.shangyi.business.R

/**
 * Date:2020/5/11
 * author:lwb
 * Desc:
 */
class MySyTextCusView constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_mysy_text, this,true)
    }


}