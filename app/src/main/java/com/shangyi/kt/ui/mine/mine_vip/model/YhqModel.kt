package com.shangyi.kt.ui.mine.mine_vip.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.LogUtils
import com.shangyi.kt.ui.mine.bean.YhqListBean

/**
 * Date:2020/5/7
 * author:lwb
 * Desc:
 */
class YhqModel : BaseViewModel() {

    val yhqData = MutableLiveData<YhqListBean?>()

    /**
     * 获取优惠券信息
     */
    fun loadYhuData(
            type: Int,
            page: Int
    ) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            if (type != 0) {
                params.put("type", type)
            }
            params.put("page", page)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.getYhqData(params.aesData)
        }, {
            mIsLoadingShow.value = false
            yhqData.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}