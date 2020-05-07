package com.shangyi.kt.fragment.home.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.kt.fragment.categroy.bean.CategroyRightBean

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class HomeModel:BaseViewModel() {

    var categoryRightData = MutableLiveData<CategroyRightBean?>()


    /**
     * 获取右侧分类数据
     */
    fun getRightCategory(categroyId: Int) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("id", categroyId)
            RetrofitClient.apiCusService.getRightCategory(params.aesData)
        }, {
            categoryRightData.value = it
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            //            UIUtils.showToast(msg)
            categoryRightData.value = null
            mIsLoadingShow.value = false
        })
    }


}