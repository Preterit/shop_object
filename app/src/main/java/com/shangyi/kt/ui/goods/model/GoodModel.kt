package com.shangyi.kt.ui.goods.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.sdxxtop.network.utils.AESUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Constants
import com.shangyi.business.network.Params
import com.shangyi.business.network.SpUtil
import com.shangyi.kt.ui.goods.bean.GoodsDetailBean
import com.shangyi.kt.ui.goods.bean.GoodsListBean

/**
 * Date:2020/4/9
 * author:lwb
 * Desc:
 */
class GoodDetailModel : BaseViewModel() {

    var data = MutableLiveData<GoodsDetailBean>()

    /**
     * 商品信息
     */
    fun loadGoodsInfo(goodsId: Int) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("id", goodsId)
            params.put("uid", 0)
            Log.e("data --- ", "${AESUtils.decrypt(params.aesData, SpUtil.getString(Constants.API_KEY))}")
            RetrofitClient.apiCusService.getGoodsInfo(params.aesData)
        }, {
            mIsLoadingShow.value = false
            data.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}

class GoodsListModel : BaseViewModel() {

    var goodsList = MutableLiveData<List<GoodsListBean>>()

    /**
     *  商品列表数据
     */
    fun loadGoodsData(
            categroyId: Int,
            pageSize: Int,
            order: String,
            order_value: String,
            isFirstLoad: Boolean
    ) {
        loadOnUI({
            if (isFirstLoad) {
                showLoadingDialog(true)
            }
            val params = Params()
            params.put("cid", categroyId)
            params.put("page", pageSize)
            params.put("order", order)
            params.put("order_value", order_value)
            Log.e("data -- ", "${AESUtils.decrypt(params.aesData, SpUtil.getString(Constants.API_KEY))}")
            RetrofitClient.apiCusService.getGoodsList(params.aesData)
        }, {
            goodsList.value = it
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

}