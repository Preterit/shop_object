package com.shangyi.kt.ui.order.model

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
import com.shangyi.kt.ui.pingjia.OrderDataBean

/**
 * data: 2020/4/21 11:39
 * auther: Dz
 * function:
 */
class OrderSuccessModel:BaseViewModel() {

    var successData = MutableLiveData<List<OrderBean>?>()

    var orderData = MutableLiveData<List<OrderDataBean>?>()

    /**
     * 商品推荐
     */
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

    /**
     * 确认下单
     */
    fun querenOrder(uid: Int ,
                    address_id : Int) {
        loadOnUI({
            val params = Params()
            params.put("uid", 1)
            params.put("address_id", 1)
            Log.e("data -- ", "${AESUtils.decrypt(params.aesData, SpUtil.getString(Constants.API_KEY))}")
            RetrofitClient.apiCusService.querenOrder(params.aesData)
        }, {
            mIsLoadingShow.value = false
            //orderData.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}