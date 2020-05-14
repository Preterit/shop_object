package com.shangyi.kt.ui.order.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.LogUtils
import com.shangyi.kt.ui.order.bean.OrderDetailInfoBean

/**
 * Date:2020/5/11
 * author:lwb
 * Desc:
 */
class OrderDetailModel : BaseViewModel() {

    val orderInfo = MutableLiveData<OrderDetailInfoBean?>()

    /**
     * 获取订单信息
     */
    fun loadOrderInfo(orderNum: String) {
        showLoadingDialog(true)
        loadOnUI({
            val params = Params()
            params.put("order_num", orderNum)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.loadOrderInfo(params.aesData)
        }, { it ->
            orderInfo.value = it
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    /**
     * 延迟收货
     */
    fun postYcsh(orderNum: String) {
        showLoadingDialog(true)
        loadOnUI({
            val params = Params()
            params.put("order_num", orderNum)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.loadOrderInfo(params.aesData)
        }, { it ->
            orderInfo.value = it
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    /**
     * 取消退款
     */
    fun cancelRefund(orderNum: String) {
        showLoadingDialog(true)
        loadOnUI({
            val params = Params()
            params.put("order_num", orderNum)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.cancelOrderRefund(params.aesData)
        }, { it ->
            mIsLoadingShow.value = false
            UIUtils.showToast("取消退款成功")
            loadOrderInfo(orderNum)
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }


}