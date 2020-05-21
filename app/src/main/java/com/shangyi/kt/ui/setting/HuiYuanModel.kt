package com.shangyi.kt.ui.setting

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
class HuiYuanModel : BaseViewModel() {

    val mineInfo = MutableLiveData<MineBean?>()

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

}