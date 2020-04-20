package com.shangyi.kt.ui.address.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.sdxxtop.network.utils.AESUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Constants
import com.shangyi.business.network.Params
import com.shangyi.business.network.SpUtil
import com.shangyi.kt.ui.address.bean.AreaBean

/**
 * Date:2020/4/19
 * author:lwb
 * Desc:
 */
class AddAddressModel : BaseViewModel() {

    private var mRefreshListener: OnAreaRefresh? = null
    var isAddSuccess = MutableLiveData<Boolean>()

    /**
     * 默认查询省份。city查询城市，county查询区县（type为city或county，id必须存在）
     */
    fun getAreaData(id: Int, type: Int) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            if (id != -1) {
                params.put("id", id)
            }
            params.put("type", type)

            Log.e("data --- ", "${AESUtils.decrypt(params.aesData, SpUtil.getString(Constants.API_KEY))}")
            RetrofitClient.apiCusService.getAreaData(params.aesData)
        }, {
            mIsLoadingShow.value = false
            if (it != null) {
                mRefreshListener?.refresh(it)
            }
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    interface OnAreaRefresh {
        fun refresh(area: AreaBean)
    }

    fun setRefreshListener(listtener: OnAreaRefresh) {
        this.mRefreshListener = listtener
    }

    fun saveAddress(
            name: String,
            number: String,
            addresDetail: String,
            id1: Int,
            id2: Int,
            id3: Int,
            default: Int
    ) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
//            if (id != -1) {
//                params.put("id", id)
//            }
            params.put("uid", SpUtil.getInt(Constants.USER_ID, 0))
            params.put("name", name)
            params.put("mobile", number)
            params.put("province", id1)
            params.put("city", id2)
            params.put("county", id3)
            params.put("address", addresDetail)
            params.put("default", default)

            Log.e("data --- ", "${AESUtils.decrypt(params.aesData, SpUtil.getString(Constants.API_KEY))}")
            RetrofitClient.apiCusService.saveAddress(params.aesData)
        }, {
            mIsLoadingShow.value = false
            isAddSuccess.value = true
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }


    /**
     * 加载地址列表
     */
    fun loadAddressList() {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()

            Log.e("data --- ", "${AESUtils.decrypt(params.aesData, SpUtil.getString(Constants.API_KEY))}")
            RetrofitClient.apiCusService.getAddressList(params.aesData)
        }, {
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}