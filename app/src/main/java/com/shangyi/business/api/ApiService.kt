package com.shangyi.business.api

import com.sdxxtop.network.helper.data.BaseResponse
import com.shangyi.kt.fragment.bean.CategroyLeftBean
import com.shangyi.kt.fragment.bean.CategroyRightBean
import com.shangyi.kt.fragment.car.entity.CartInfo
import com.shangyi.kt.ui.address.bean.AreaBean
import com.shangyi.kt.ui.address.bean.AreaListBean
import com.shangyi.kt.ui.goods.bean.GoodsDetailBean
import com.shangyi.kt.ui.goods.bean.GoodsListBean
import com.shangyi.kt.ui.goods.bean.GoodsSpecBean
import com.shangyi.kt.ui.goods.bean.ReecommendGood
import com.shangyi.kt.ui.order.bean.OrderDataBean
import com.shangyi.kt.ui.pingjia.OrderBean
import com.shangyi.kt.ui.pingjia.bean.PingjiaDataBean
import com.shangyi.kt.ui.splash.bean.GetSettingBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Email: zhousaito@163.com
 * Created by zhousaito 2019-07-29 20:21
 * Version: 1.0
 * Description:
 */

interface ApiService {

    companion object {
//        const val BASE_URL = "http://yinanapi.sdxxtop.com/api/"
//        const val BASE_URL = "http://envir.test.sdxxtop.com/api/"
    }

    @FormUrlEncoded
    @POST("/api/sys_config/getConfigInfo")
    suspend fun getSetting(@Field("data") data: String): BaseResponse<GetSettingBean?>

    @FormUrlEncoded
    @POST("/api/sys_config/getConfigInfo")
    suspend fun getAESSetting(@Field("data") data: String): BaseResponse<GetSettingBean?>


    @FormUrlEncoded
    @POST("/api/login/login")
    suspend fun login(@Field("data") data: String): BaseResponse<String?>

    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST("/api/login/send_code")
    suspend fun getCode(@Field("data") data: String): BaseResponse<String?>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("/api/login/register")
    suspend fun register(@Field("data") data: String): BaseResponse<String?>

    /**
     * 完善个人信息
     */
    @FormUrlEncoded
    @POST("/api/login/improveInfo")
    suspend fun commitInfo(@Field("data") data: String): BaseResponse<String?>

    /**
     * 找回密码
     */
    @FormUrlEncoded
    @POST("/api/login/findPwd")
    suspend fun findpwd(@Field("data") data: String): BaseResponse<String?>

    /**
     * 分类左侧分类数据
     */
    @FormUrlEncoded
    @POST("/api/goods_category/goodsCateList")
    suspend fun getLeftCategory(@Field("data") data: String): BaseResponse<List<CategroyLeftBean>?>

    /**
     * 分类右侧分类数据
     */
    @FormUrlEncoded
    @POST("/api/goods_category/goodsCateList")
    suspend fun getRightCategory(@Field("data") data: String): BaseResponse<CategroyRightBean?>

    /**
     * 找回密码
     */
    @FormUrlEncoded
    @POST("/api/goods/goodsList")
    suspend fun getGoodsList(@Field("data") data: String): BaseResponse<List<GoodsListBean>?>

    /**
     * 商品详情——商品信息
     */
    @FormUrlEncoded
    @POST("/api/Goods/getGoodsInfo")
    suspend fun getGoodsInfo(@Field("data") data: String): BaseResponse<GoodsDetailBean?>

    /**
     * 商品详情——商品规格
     */
    @FormUrlEncoded
    @POST("/api/goods/getGoodsSpec")
    suspend fun getGoodsSpec(@Field("data") data: String): BaseResponse<GoodsSpecBean?>

    /**
     * 商品详情——获取地址
     */
    @FormUrlEncoded
    @POST("/api/user/getProviceCity")
    suspend fun getAreaData(@Field("data") data: String): BaseResponse<AreaBean?>

    /**
     * 商品详情——添加地址
     */
    @FormUrlEncoded
    @POST("/api/user/addReceiveAddress")
    suspend fun saveAddress(@Field("data") data: String): BaseResponse<Any?>

    /**
     * 地址——列表
     */
    @FormUrlEncoded
    @POST("/api/user/getAddressList")
    suspend fun getAddressList(@Field("data") data: String): BaseResponse<List<AreaListBean>?>

    /**
     * 地址——删除地址
     */
    @FormUrlEncoded
    @POST("/api/user/delReceiveAddress")
    suspend fun deleteAddress(@Field("data") data: String): BaseResponse<Any?>

    /**
     * 评论——列表
     */
    @FormUrlEncoded
    @POST("/api/goods_comment/commentList")
    suspend fun pinglunList(@Field("data") data: String): BaseResponse<PingjiaDataBean?>

    /**
     * 支付成功  商品推荐
     */
    @FormUrlEncoded
    @POST("/api/goods/getShopRecommend")
    suspend fun successOrdertuijian(@Field("data") data: String): BaseResponse<List<OrderBean>?>


    /**
     * 订单提交
     */
    @FormUrlEncoded
    @POST("/api/Orders/placeOrder")
    suspend fun querenOrder(@Field("data") data: String): BaseResponse<Any?>

    /**
     * 购物车 -- 列表
     */
    @FormUrlEncoded
    @POST("/api/cart/cartList")
    suspend fun getCarList(@Field("data") data: String): BaseResponse<List<CartInfo?>?>

    /**
     * 购物车 -- 添加购物车
     */
    @FormUrlEncoded
    @POST("/api/cart/addCart")
    suspend fun addCar(@Field("data") data: String): BaseResponse<Any?>

    /**
     * 购物车 -- 购物车推荐
     */
    @FormUrlEncoded
    @POST("/api/user/getUserRecommendGoods")
    suspend fun getLookMoreData(@Field("data") data: String): BaseResponse<List<ReecommendGood?>?>

    /**
     * 购物车 -- 删除商品
     */
    @FormUrlEncoded
    @POST("/api/cart/cartDelete")
    suspend fun delCarGoods(@Field("data") data: String): BaseResponse<Any?>
}