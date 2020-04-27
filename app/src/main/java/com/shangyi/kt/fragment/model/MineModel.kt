package com.shangyi.kt.fragment.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params

/**
 * Date:2020/4/27
 * author:lwb
 * Desc:
 */
class MineModel : BaseViewModel() {

    val mineInfo = MutableLiveData<Boolean>()

    /**
     * 获取个人信息
     */
    fun loadMineInfo() {
        loadOnUI({
            val params = Params()
            RetrofitClient.apiCusService.getUserInfo(params.aesData)
        }, {
            mineInfo.value = it == null
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mineInfo.value = true
        })
    }
}