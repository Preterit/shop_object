package com.shangyi.kt.ui.pingjia.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.sdxxtop.network.utils.AESUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Constants
import com.shangyi.business.network.Params
import com.shangyi.business.network.SpUtil
import com.shangyi.kt.ui.pingjia.bean.PingjiaDataBean

/**
 * Date:2020/4/21
 * author:lwb
 * Desc:
 */
class PingjiaModel : BaseViewModel() {

    var pingjiaData = MutableLiveData<PingjiaDataBean?>()

    /**
     * 评价列表
     */
    fun loadPingjiaData(goodId: Int, page: Int, type: Int, img: Int, isFirst: Boolean) {
        loadOnUI({
            if (isFirst) {
                showLoadingDialog(true)
            }
            val params = Params()
            params.put("gid", goodId)
            params.put("page", page)
            if (type != 0) {
                params.put("type", type)
            }
            params.put("img", img)
            Log.e("data --- ", "${AESUtils.decrypt(params.aesData, SpUtil.getString(Constants.API_KEY))}")
            RetrofitClient.apiCusService.pinglunList(params.aesData)
        }, {
            mIsLoadingShow.value = false
            pingjiaData.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}