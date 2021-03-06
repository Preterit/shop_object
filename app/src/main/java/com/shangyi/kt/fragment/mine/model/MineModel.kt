package com.shangyi.kt.fragment.mine.model

import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.LogUtils
import com.shangyi.kt.fragment.mine.bean.MineBean
import com.shangyi.kt.ui.goods.bean.ReecommendGood

/**
 * Date:2020/4/27
 * author:lwb
 * Desc:
 */
class MineModel : BaseViewModel() {

    val mineInfo = MutableLiveData<MineBean?>()
    val itemListData = MutableLiveData<List<ReecommendGood?>?>()

    /**
     * 获取个人信息
     */
    fun loadMineInfo() {
        loadOnUI({
            val params = Params()
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.getUserInfo(params.aesData)
        }, {
            mineInfo.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mineInfo.value = null
        })
    }

    /**
     * 获取该分类下的推荐商品
     */
    fun loadFmListData(itemId: Int) {
        loadOnUI({
            val params = Params()
            params.put("cid", itemId)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.getCateTjData(params.aesData)
        }, {
            itemListData.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            itemListData.value = null
        })
    }
}