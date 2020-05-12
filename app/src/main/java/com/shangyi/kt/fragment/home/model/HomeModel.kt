package com.shangyi.kt.fragment.home.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.kt.fragment.categroy.bean.CategroyRightBean

/**
 * Date:2020/4/7
 * author:lwb
 * Desc:
 */
class HomeModel : BaseViewModel() {

    var homeBanner = MutableLiveData<List<HomeBanner>?>()
    var listData = MutableLiveData<List<HomeDataBean>?>()

    /**
     * 获取右侧分类数据
     */
    fun getHomeBannerData() {
        loadOnUI({
            val params = Params()
            RetrofitClient.apiCusService.getHomeBanner(params.aesData)
        }, {
            mIsLoadingShow.value = false
            homeBanner.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    /**
     * 获取适配器数据
     */
    fun getListData() {
        loadOnUI({
            val params = Params()
            RetrofitClient.apiCusService.getListData(params.aesData)
        }, {
            listData.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
        })
    }

}