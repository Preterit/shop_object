package com.shangyi.kt.fragment.model

import android.util.Log
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CategroyModel : BaseViewModel() {
    /**
     * 获取分类
     */
    fun getCategory() {
        loadOnUI({
            val params = Params()
            RetrofitClient.apiCusService.getCategory(params.aesData)
        }, {
            if (it != null) {
                Log.e("CategroyModel --- ", "${it.toString()}")
            }
        }, { code, msg, t ->
            UIUtils.showToast(msg)
        })
    }
}