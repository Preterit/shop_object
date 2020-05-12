package com.shangyi.kt.fragment.categroy.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.kt.fragment.categroy.bean.CategroyLeftBean
import com.shangyi.kt.fragment.categroy.bean.CategroyRightBean

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class CategroyModel : BaseViewModel() {

    var categoryLeftData = MutableLiveData<List<CategroyLeftBean>?>()
    var categoryRightData = MutableLiveData<CategroyRightBean?>()

    /**
     * 获取分类
     */
    fun getLeftCategory() {
        loadOnUI({
            val params = Params()
            RetrofitClient.apiCusService.getLeftCategory(params.aesData)
        }, {
            if (it != null) {
                categoryLeftData.value = it
            }
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            categoryLeftData.value = null
        })
    }

    /**
     * 获取右侧分类数据
     */
    fun getRightCategory(categroyId: Int) {
        loadOnUI({
//            showLoadingDialog(true)
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