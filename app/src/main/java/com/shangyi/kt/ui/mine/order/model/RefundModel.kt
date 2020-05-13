package com.shangyi.kt.ui.mine.order.model

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

    /**
     * 申请退款
     */
    fun refundOrder(
            orderNum: String,
            gid: Int,
            remark: String,
            explain: String
    ) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("order_num", orderNum)
            params.put("gid", gid)
            params.put("remark", remark)
            params.put("explain", explain)

            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.orderRefund(params.aesData)
        }, {
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}