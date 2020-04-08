package com.shangyi.business.utils

import com.shangyi.business.MyApplication

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
fun Int.dp2px():Int{
    return ViewUtil.dp2px(MyApplication.getInstance().applicationContext,this)
}