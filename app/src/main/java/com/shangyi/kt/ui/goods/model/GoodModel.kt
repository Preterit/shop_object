package com.shangyi.kt.ui.goods.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sdxxtop.base.BaseViewModel
import com.sdxxtop.base.utils.UIUtils
import com.sdxxtop.network.utils.AESUtils
import com.shangyi.business.api.RetrofitClient
import com.shangyi.business.network.Constants
import com.shangyi.business.network.Params
import com.shangyi.business.network.SpUtil
import com.shangyi.kt.fragment.bean.ChildBean
import com.shangyi.kt.ui.goods.bean.*
import com.wuhenzhizao.sku.bean.Product
import com.wuhenzhizao.sku.bean.Sku
import com.wuhenzhizao.sku.bean.SkuAttribute

/**
 * Date:2020/4/9
 * author:lwb
 * Desc:
 */
class GoodDetailModel : BaseViewModel() {

    var data = MutableLiveData<GoodsDetailBean>()
    val product = MutableLiveData<Product?>()

    /**
     * 商品信息
     */
    fun loadGoodsInfo(goodsId: Int) {
        loadOnUI({
            showLoadingDialog(true)
            val params = Params()
            params.put("id", goodsId)
            params.put("uid", 0)
            Log.e("data --- ", "${AESUtils.decrypt(params.aesData, SpUtil.getString(Constants.API_KEY))}")
            RetrofitClient.apiCusService.getGoodsInfo(params.aesData)
        }, {
            mIsLoadingShow.value = false
            data.value = it
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    /**
     * 获取代金券的描述信息
     */
    fun getYouhuiquanStr(bean: YouhuiquanBean): String {
        return when (bean.type) {
            1 -> {  // 满减
                "${"领券满" + bean.full_price + "减" + bean.price}"
            }
            2 -> {   // 代金券
                "${"领券立减" + bean.price}"
            }
            else -> ""
        }
    }

    /**
     * 获取规格的描述信息
     */
    fun getStandardStr(it: GoodsDetailBean): String {
        return if (it.spec != null) {
            "${it.spec.value},1${it.goods_unit?.name}"
        } else {
            ""
        }
    }

    /**
     * 加载商品规格。
     */
    fun loadGoodsSpec(goodsId: Int) {
        loadOnUI({
            val params = Params()
            params.put("id", 4)
            RetrofitClient.apiCusService.getGoodsSpec(params.aesData)
        }, {
            mIsLoadingShow.value = false
            if (it != null) {
                getGoodsSpecData(it)
            }
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

    private val sku = ArrayList<Sku>()

    /**
     * 处理规格数据
     */
    private fun getGoodsSpecData(data: GoodsSpecBean) {
        val productResult = Product()
        if (isListEmpty(data.list)) {
            data.list?.forEach {
                val skuAttr = ArrayList<SkuAttribute>()
                var index = 0
                skuAttr.add(SkuAttribute(data.keys[index], it?.name))
                getChildData(it?.child, index, data, skuAttr, it)
            }



            productResult.id = "11"
            productResult.name = "测试"
            productResult.stockQuantity = 100000
            productResult.skus = sku
            product.value = productResult
        }
    }


    /**
     * 判断list是否为空
     */
    private fun isListEmpty(list: List<Any?>?): Boolean {
        return !list.isNullOrEmpty()
    }

    private fun getChildData(childList: List<SkuBean?>?, _index: Int, data: GoodsSpecBean, skuAttr: ArrayList<SkuAttribute>, skuBean: SkuBean?) {
        var index = _index
        if (isListEmpty(childList)) {
            index++
            childList?.forEach {
                // 第二次 型号
                val listTemp = ArrayList<SkuAttribute>(skuAttr)
                if (isListEmpty(it?.child)) {
                    listTemp.add(SkuAttribute(data.keys[index], it?.name))
                    getChildData(it?.child, index, data, listTemp, it)
                } else {
                    listTemp.add(SkuAttribute(data.keys[index], it?.name))
                    sku.add(Sku(skuBean?.id.toString(), "", 20220, 22222, listTemp))
                }
            }
        } else {
            sku.add(Sku(skuBean?.id.toString(), "", 20220, 22222, skuAttr))
        }
    }
}

class GoodsListModel : BaseViewModel() {

    var goodsList = MutableLiveData<List<GoodsListBean>>()

    /**
     *  商品列表数据
     */
    fun loadGoodsData(
            categroyId: Int,
            pageSize: Int,
            order: String,
            order_value: String,
            isFirstLoad: Boolean
    ) {
        loadOnUI({
            if (isFirstLoad) {
                showLoadingDialog(true)
            }
            val params = Params()
            params.put("cid", categroyId)
            params.put("page", pageSize)
            params.put("order", order)
            params.put("order_value", order_value)
            Log.e("data -- ", "${AESUtils.decrypt(params.aesData, SpUtil.getString(Constants.API_KEY))}")
            RetrofitClient.apiCusService.getGoodsList(params.aesData)
        }, {
            goodsList.value = it
            mIsLoadingShow.value = false
        }, { code, msg, t ->
            UIUtils.showToast(msg)
            mIsLoadingShow.value = false
        })
    }

}