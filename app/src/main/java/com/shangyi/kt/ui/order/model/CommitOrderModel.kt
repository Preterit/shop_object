package com.shangyi.kt.ui.order.model

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.kt.ui.address.bean.AreaListBean
import com.shangyi.kt.ui.order.bean.OrderListJsonBean
import java.util.ArrayList

/**
 * Date:2020/4/24
 * author:lwb
 * Desc:
 */
class CommitOrderModel : BaseViewModel() {

    private val gson = Gson()
    var areaBean = MutableLiveData<AreaListBean?>()
    var yfData = MutableLiveData<Float>()

    /**
     * 获取默认地址
     */
    fun loadAddress() {
        loadOnUI({
            showLoadingDialog(true)
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
            showLoadingDialog(true)
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
            RetrofitClient.apiCusService.querenOrder(params.aesData)
        }, { it ->
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}