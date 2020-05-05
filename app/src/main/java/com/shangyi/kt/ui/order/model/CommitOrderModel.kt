package com.shangyi.kt.ui.order.model

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.LogUtils
import com.shangyi.kt.ui.address.bean.AreaListBean
import com.shangyi.kt.ui.order.bean.OrderListJsonBean
import com.shangyi.kt.ui.order.bean.OrderPayBefore
import com.shangyi.kt.ui.order.bean.WxRequest
import java.util.*

/**
 * Date:2020/4/24
 * author:lwb
 * Desc:
 */
class CommitOrderModel : BaseViewModel() {

    private val gson = Gson()
    var areaBean = MutableLiveData<AreaListBean?>()
    var yfData = MutableLiveData<Float>()
    var querenOrders = MutableLiveData<OrderPayBefore>()
    var orderInfo = MutableLiveData<String?>()
    var wxPayInfo = MutableLiveData<WxRequest?>()
    var aliPaySuccess = MutableLiveData<Boolean>()

    /**
     * 获取默认地址
     */
    fun loadAddress() {
        loadOnUI({
            val params = Params()
            RetrofitClient.apiCusService.getAddressList(params.aesData)
        }, { it ->
            mIsLoadingShow.value = false
            if (it == null) return@loadOnUI
            for (element in it) {
                if (element.is_default == 1) {
                    areaBean.value = element
                    break
                }
            }
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    /**
     * 获取运费
     */
    fun loadYunfei(
            goodsList: List<OrderListJsonBean>,
            addressId: Int
    ) {
        loadOnUI({
            val params = Params()
            params.put("list", gson.fromJson(gson.toJson(goodsList), List::class.java))
            params.put("address_id", addressId)
            RetrofitClient.apiCusService.loadYunfei(params.aesData)
        }, { it ->
            mIsLoadingShow.value = false
            yfData.value = it?.delivery
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    /**
     * 提交订单
     */
    fun commitOrder(goodsList: ArrayList<OrderListJsonBean>, addressId: Int) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("list", gson.fromJson(gson.toJson(goodsList), List::class.java))
            params.put("address_id", addressId)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.querenOrders(params.aesData)
        }, { it ->
            mIsLoadingShow.value = false
            querenOrders.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    /**
     * 获取支付前的支付信息
     */
    fun getPayInfo(orderId: String, payType: Int) {
        loadOnUI({
            val params = Params()
            params.put("order_id", orderId)
            params.put("pay_type", if (payType == 1) "zfb" else "wx")
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.getPayInfo(params.aesData)
        }, { it ->
            mIsLoadingShow.value = false
            orderInfo.value = it?.info
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    /**
     * 查询订单是否支付成功
     */
    fun notifyOrder(orderNumber: String) {
        loadOnUI({
            val params = Params()
            params.put("order_num", orderNumber)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.getOrderStatus(params.aesData)
        }, { it ->
            mIsLoadingShow.value = false
            aliPaySuccess.value = true
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
            aliPaySuccess.value = false
        })
    }

    /**
     * 获取微信支付的订单信息
     */
    fun getWxPayInfo(orderId: String, payType: Int) {
        loadOnUI({
            val params = Params()
            params.put("order_id", orderId)
            params.put("pay_type", "wx")
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.getWxPayInfo(params.aesData)
        }, { it ->
            mIsLoadingShow.value = false
            wxPayInfo.value = it?.info
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}