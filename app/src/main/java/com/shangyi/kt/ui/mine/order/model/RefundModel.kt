package com.shangyi.kt.ui.mine.order.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.LogUtils

/**
 * Date:2020/5/13
 * author:lwb
 * Desc:
 */
class RefundModel : BaseViewModel() {

    val refundSuccess = MutableLiveData<Boolean>(false)

    /**
     * 申请退款
     */
    fun refundOrder(
            orderNum: String,
            gid: Int,
            remark: String,
            explain: String,
            orderRid: String
    ) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("order_num", orderNum)
            params.put("gid", gid)
            params.put("remark", remark)
            params.put("explain", explain)
            if (!orderRid.isNullOrEmpty()) {
                params.put("order_refund_id", orderRid)
            }
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.orderRefund(params.aesData)
        }, {
            mIsLoadingShow.value = false
            refundSuccess.value = true
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}