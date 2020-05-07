package com.shangyi.kt.ui.mine.mine_vip.model

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Params
import com.shangyi.business.utils.LogUtils
import com.shangyi.kt.ui.mine.bean.CollectListBean
import com.shangyi.kt.ui.mine.bean.Good

/**
 * Date:2020/5/7
 * author:lwb
 * Desc:
 */
class CollectModel : BaseViewModel() {

    var collectData = MutableLiveData<List<CollectListBean>?>()
    val list = ArrayList<Int>()

    /**
     * 获取收藏列表
     */
    fun loadCollectData(page: Int) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("limit", page)
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.getCollectList(params.aesData)
        }, {
            mIsLoadingShow.value = false
            collectData.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    /**
     * 删除收藏
     */
    fun delCollect(goods: List<Good>) {
        goods.forEach {
            list.add(it.id)
        }
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("gid", Gson().fromJson(list.toString(), List::class.java))
            LogUtils.deCodeParams(params)
            RetrofitClient.apiCusService.delCollect(params.aesData)
        }, {
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }
}