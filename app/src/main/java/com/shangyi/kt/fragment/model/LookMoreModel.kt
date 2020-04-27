package com.shangyi.kt.fragment.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.kt.ui.goods.bean.ReecommendGood

/**
 * Date:2020/4/23
 * author:lwb
 * Desc:
 */
class LookMoreModel : BaseViewModel() {

    val lookMoreData = MutableLiveData<List<ReecommendGood?>?>()

    /**
     * 查看更多
     */
    fun loadLookMoreData() {
        loadOnUI({
            val params = Params()
            RetrofitClient.apiCusService.getLookMoreData(params.aesData)
        }, {
            lookMoreData.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            lookMoreData.value = null
        })
    }

}