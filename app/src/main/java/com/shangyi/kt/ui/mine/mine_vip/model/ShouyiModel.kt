package com.shangyi.kt.ui.mine.mine_vip.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.LogUtils
import com.shangyi.kt.ui.mine.bean.MyDataBean

/**
 * Date:2020/5/10
 * author:lwb
 * Desc:
 */
class ShouyiModel : BaseViewModel() {

    val dataList = MutableLiveData<MyDataBean>()

    /**
     * 获取收益
     */
    fun getShouyi() {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.getSyData(params.aesData)
        }, {
            mIsLoadingShow.value = false
            dataList.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}