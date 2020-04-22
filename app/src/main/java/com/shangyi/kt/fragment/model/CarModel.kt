package com.shangyi.kt.fragment.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.kt.fragment.bean.CarDataBean

/**
 * Date:2020/4/22
 * author:lwb
 * Desc:
 */
class CarModel : BaseViewModel() {

    var carList = MutableLiveData<List<CarDataBean?>>()

    /**
     * 获取购物车列表
     */
    fun getCarList() {
        loadOnUI({
            val params = Params()
            RetrofitClient.apiCusService.getCarList(params.aesData)
        }, {
            carList.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
        })
    }
}