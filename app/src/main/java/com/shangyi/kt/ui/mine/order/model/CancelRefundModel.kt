package com.shangyi.kt.ui.mine.order.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.LogUtils
import com.shangyi.kt.ui.mine.bean.RefundOrderBean

/**
 * Date:2020/5/14
 * author:lwb
 * Desc:
 */
class CancelRefundModel : BaseViewModel() {

    val cancelRefundSuccess = MutableLiveData<Boolean>(false)
    val refundData = MutableLiveData<RefundOrderBean>()

    /**
     * 取消退款
     */
    fun cancelRefund(orderNum: String) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("order_num", orderNum)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.cancelOrderRefund(params.aesData)
        }, {
            mIsLoadingShow.value = false
            cancelRefundSuccess.value = true
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    /**
     * 获取退款订单信息
     */
    fun loadRefundData(rid:String) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("rid", rid)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.getRefundOrder(params.aesData)
        }, {
            mIsLoadingShow.value = false
            refundData.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}