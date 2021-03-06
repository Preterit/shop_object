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
    val delSuccess = MutableLiveData<Boolean>(false)

    /**
     * 获取优惠券信息
     */
    fun loadYhuData(
            type: Int,
            page: Int
    ) {
        loadOnUI({
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

    /**
     * 删除优惠券
     */
    fun delYhq(id: Int?) {
        /*loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("receive_id", id)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.delYhq(params.aesData)
        }, {
            mIsLoadingShow.value = false
            delSuccess.value = true
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            delSuccess.value = false
            mIsLoadingShow.value = false
        })*/
    }
}