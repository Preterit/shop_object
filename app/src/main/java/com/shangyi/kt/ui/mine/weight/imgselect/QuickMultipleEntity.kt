package com.shangyi.kt.ui.mine.weight.imgselect

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * Date:2020/5/18
 * author:lwb
 * Desc:
 */
class QuickMultipleEntity(val path: String, override val itemType: Int) : MultiItemEntity {

    companion object {
        const val CHOOSE_FLAG = 0
        const val CENTER_FLAG = 1
    }
}