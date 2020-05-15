package com.shangyi.kt.ui.home.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.LogUtils
import com.shangyi.kt.fragment.home.model.HomeDataBean
import com.shangyi.kt.ui.home.bean.GaoYongBean
import com.shangyi.kt.ui.order.bean.OrderBean
import com.study.glidemodel.progress.ProgressAppGlideModule

/**
 * data: 2020/4/21 11:39
 * auther: Dz
 * function:
 */
class HomeGaoyongModel:BaseViewModel() {

    var successData = MutableLiveData<List<GaoYongBean>>()

    /**
     * 商品推荐
     */
    fun gaoYongTuijian(module: String) {
        loadOnUI({
            val params = Params()
            params.put("module", "001")
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.gaoyongTuijian(params.aesData)
        }, {
            mIsLoadingShow.value = false
            successData.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }


}