package com.shangyi.kt.ui.mine.order.model

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.LogUtils
import com.shangyi.kt.ui.mine.bean.OrderListBean

/**
 * Date:2020/5/6
 * author:lwb
 * Desc:
 */
class OrderListFragmentModel : BaseViewModel() {
    val data = MutableLiveData<List<OrderListBean>?>()

    /**
     * 获取订单列表
     */
    fun loadOrderListData(
            page: Int,
            type: Int
    ) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("page", page)
            if (type != -1) {
                params.put("status", type)
            }
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.getAllOrders(params.aesData)
        }, {
            mIsLoadingShow.value = false
            data.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
            data.value = null
        })
    }

    /**
     * 延迟收货
     */
    fun postYcsh(orderNum: String) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("order_num", orderNum)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.postYcsh(params.aesData)
        }, {
            mIsLoadingShow.value = false
            UIUtils.showToast("延迟收货设置成功")
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    /**
     * 取消订单
     */
    fun postCancelOrder(orderNum: String, remark: String) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("order_num", orderNum)
            params.put("remark", remark)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.postCancelOrder(params.aesData)
        }, {
            mIsLoadingShow.value = false
            UIUtils.showToast("取消成功")
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    /**
     * 修改地址
     */
    fun changeAddress(orderNum: String) {

    }
}