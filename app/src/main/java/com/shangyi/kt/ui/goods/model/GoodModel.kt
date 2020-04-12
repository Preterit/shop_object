package com.shangyi.kt.ui.goods.model

import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params

/**
 * Date:2020/4/9
 * author:lwb
 * Desc:
 */
class GoodDetailModel : BaseViewModel() {
    fun loadGoodsInfo() {
        loadOnUI({
            val params = Params()
            params.put("id", 1)
            params.put("uid", 0)
            RetrofitClient.apiCusService.getGoodsInfo(params.aesData)
        }, {
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
        })
    }
}

class GoodsListModel : BaseViewModel() {
    fun loadGoodsData(
            categroyId: Int
    ) {
        loadOnUI({
            val params = Params()
            params.put("cid", categroyId)
            params.put("page", 1)
            params.put("order", "sales——")
            params.put("order_value", "sales——")
            RetrofitClient.apiCusService.getGoodsList("")
        }, {
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
        })
    }

}