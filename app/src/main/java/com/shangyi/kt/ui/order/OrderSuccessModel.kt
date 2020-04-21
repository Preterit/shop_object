package com.shangyi.kt.ui.order

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.sdxxtop.network.utils.AESUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Constants
import com.shangyi.business.network.Params
import com.shangyi.business.network.SpUtil
import com.shangyi.kt.ui.pingjia.OrderBean

/**
 * data: 2020/4/21 11:39
 * auther: Dz
 * function:
 */
class OrderSuccessModel:BaseViewModel() {

    var successData = MutableLiveData<List<OrderBean>?>()

    fun successOrderTuijian(shop_id: Int) {
        loadOnUI({
            val params = Params()
            params.put("shop_id", 1)
            Log.e("data -- ", "${AESUtils.decrypt(params.aesData, SpUtil.getString(Constants.API_KEY))}")
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