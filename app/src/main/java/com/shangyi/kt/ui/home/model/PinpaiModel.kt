package com.shangyi.kt.ui.home.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.LogUtils
import com.shangyi.kt.fragment.home.model.HomeDataBean
import com.shangyi.kt.ui.order.bean.OrderBean

/**
 * data: 2020/4/21 11:39
 * auther: Dz
 * function:
 */
class PinpaiModel:BaseViewModel() {

    var successData = MutableLiveData<List<OrderBean>?>()

    /**
     * 商品推荐
     */
    fun successOrderTuijian(shop_id: Int) {
        loadOnUI({
            val params = Params()
            params.put("shop_id", 1)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.successOrdertuijian(params.aesData)
        }, {
            mIsLoadingShow.value = false
            successData.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }


}