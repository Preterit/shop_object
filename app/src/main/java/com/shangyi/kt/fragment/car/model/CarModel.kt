package com.shangyi.kt.fragment.car.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.LogUtils
import com.shangyi.kt.fragment.car.entity.CartInfo

/**
 * Date:2020/4/22
 * author:lwb
 * Desc:
 */
class CarModel : BaseViewModel() {
    private var mListener: OnCarDataRefresh? = null
    val delSuccess = MutableLiveData<Boolean>()

    fun setOnCarDataRefreshListener(listener: OnCarDataRefresh) {
        this.mListener = listener
    }

    /**
     * 获取购物车列表
     */
    fun getCarList() {
        loadOnUI({
            val params = Params()
            RetrofitClient.apiCusService.getCarList(params.aesData)
        }, {
            if (it == null) {
                mListener?.carDataRefresh(listOf())
            } else {
                mListener?.carDataRefresh(it)
            }
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mListener?.carDataRefresh(null)
        })
    }

    /**
     * 删除商品
     */
    fun delGoods(cid: List<Int>?, isRefresh: Boolean) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("cid", cid)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.delCarGoods(params.aesData)
        }, {
            mIsLoadingShow.value = false
            getCarList()
            delSuccess.value = true
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
            delSuccess.value = false
        })
    }

}

interface OnCarDataRefresh {
    fun carDataRefresh(it: List<CartInfo?>?)
}