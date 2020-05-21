package com.shangyi.business.api

import com.sdxxtop.network.helper.data.BaseResponse
import com.shangyi.kt.fragment.categroy.bean.CategroyLeftBean
import com.shangyi.kt.fragment.categroy.bean.CategroyRightBean
import com.shangyi.kt.fragment.mine.bean.MineBean
import com.shangyi.kt.fragment.car.entity.CartInfo
import com.shangyi.kt.fragment.home.model.HomeBanner
import com.shangyi.kt.fragment.home.model.HomeDataBean
import com.shangyi.kt.ui.address.bean.AreaBean
import com.shangyi.kt.ui.address.bean.AreaListBean
import com.shangyi.kt.ui.goods.bean.GoodsDetailBean
import com.shangyi.kt.ui.goods.bean.GoodsListBean
import com.shangyi.kt.ui.goods.bean.GoodsSpecBean
import com.shangyi.kt.ui.goods.bean.ReecommendGood
import com.shangyi.kt.ui.home.bean.*
import com.shangyi.kt.ui.mine.bean.*
import com.shangyi.kt.ui.order.bean.*
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
     * 退出登录
     */
    @FormUrlEncoded
    @POST("/api/login/logOut")
    suspend fun getQuitLogin(@Field("data") data: String): BaseResponse<Any?>


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
     * 订单 -- 提交
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

    /**
     * 订单 -- 获取运费
     */
    @FormUrlEncoded
    @POST("/api/Orders/getGoodsFreight")
    suspend fun loadYunfei(@Field("data") data: String): BaseResponse<YfDataBean?>

    /**
     * 订单 -- 提交
     */
    @FormUrlEncoded
    @POST("/api/Orders/placeOrder")
    suspend fun querenOrders(@Field("data") data: String): BaseResponse<OrderPayBefore?>

    /**
     * 订单 -- 获取支付宝支付的订单信息
     */
    @FormUrlEncoded
    @POST("/api/orders/getPayInfo")
    suspend fun getPayInfo(@Field("data") data: String): BaseResponse<OrderInfo?>

    /**
     * 订单 -- 获取微信支付的订单信息
     */
    @FormUrlEncoded
    @POST("/api/orders/getPayInfo")
    suspend fun getWxPayInfo(@Field("data") data: String): BaseResponse<WxOrderInfo?>

    /**
     * 订单 -- 查询订单支付状态
     */
    @FormUrlEncoded
    @POST("/api/orders/orderCheckStatus")
    suspend fun getOrderStatus(@Field("data") data: String): BaseResponse<Any?>

    /**
     * 订单 -- 查询订单支付状态
     */
    @FormUrlEncoded
    @POST("/api/user/getUserList")
    suspend fun getUserInfo(@Field("data") data: String): BaseResponse<MineBean?>

    /**
     * 订单 -- 查询订单支付状态
     */
    @FormUrlEncoded
    @POST("/api/user/getGoodsRecommend")
    suspend fun getCateTjData(@Field("data") data: String): BaseResponse<List<ReecommendGood?>?>

    /**
     * 订单 -- 查询订单支付状态
     */
    @FormUrlEncoded
    @POST("/api/Orders/getCouponByGoods")
    suspend fun getYhqList(@Field("data") data: String): BaseResponse<List<CommitOrderYhqData>?>

    /**
     * 订单 -- 获取全部订单
     */
    @FormUrlEncoded
    @POST("/api/user/getAllOrders")
    suspend fun getAllOrders(@Field("data") data: String): BaseResponse<List<OrderListBean>?>

    /**
     * 订单 -- 延迟收货
     */
    @FormUrlEncoded
    @POST("/api/user/delayUserOrder")
    suspend fun postYcsh(@Field("data") data: String): BaseResponse<Any>

    /**
     * 订单 -- 取消订单 -- 未支付
     */
    @FormUrlEncoded
    @POST("/api/user/cancelUserOrder")
    suspend fun postCancelOrder(@Field("data") data: String): BaseResponse<Any?>

    /**
     * 订单 -- 收藏
     */
    @FormUrlEncoded
    @POST("/api/user/CollectionGoods")
    suspend fun collectGoods(@Field("data") data: String): BaseResponse<Any?>

    /**
     * 订单 -- 收藏列表
     */
    @FormUrlEncoded
    @POST("/api/user/CollectionList")
    suspend fun getCollectList(@Field("data") data: String): BaseResponse<List<CollectListBean>?>

    /**
     * 订单 -- 取消收藏
     */
    @FormUrlEncoded
    @POST("/api/user/delCollectionGoods")
    suspend fun delCollect(@Field("data") data: String): BaseResponse<Any?>

    /**
     * 订单 -- 收藏列表
     */
    @FormUrlEncoded
    @POST("/api/user/getUserCoupon")
    suspend fun getYhqData(@Field("data") data: String): BaseResponse<YhqListBean?>

    /**
     * 首页 -- banner
     */
    @FormUrlEncoded
    @POST("/api/index/bannerList")
    suspend fun getHomeBanner(@Field("data") data: String): BaseResponse<List<HomeBanner>?>

    /**
     */
    @FormUrlEncoded
    @POST("/api/index/recommendGoods")
    suspend fun getListData(@Field("data") data: String): BaseResponse<List<HomeDataBean>?>

    /**
     */
    @FormUrlEncoded
    @POST("/api/user/CollectCoupon")
    suspend fun getYhq(@Field("data") data: String): BaseResponse<Any?>

    /**
     */
    @FormUrlEncoded
    @POST("/api/user/myEarnings")
    suspend fun getSyData(@Field("data") data: String): BaseResponse<MyDataBean?>

    /**
     * 订单 -- 订单详情信息
     */
    @FormUrlEncoded
    @POST("/api/user/getCommentOrders")
    suspend fun loadOrderInfo(@Field("data") data: String): BaseResponse<OrderDetailInfoBean?>

    /**
     * 订单 -- 修改订单地址
     */
    @FormUrlEncoded
    @POST("/api/orders/changeOrderAddress")
    suspend fun changeOrderAds(@Field("data") data: String): BaseResponse<Any?>

    /**
     * 订单 -- 确认收货
     */
    @FormUrlEncoded
    @POST("/api/user/completeOrders")
    suspend fun confirmReceipt(@Field("data") data: String): BaseResponse<Any?>

    /**
     * 订单 -- 申请退款
     */
    @FormUrlEncoded
    @POST("/api/orders/orderRefund")
    suspend fun orderRefund(@Field("data") data: String): BaseResponse<Any?>

    /**
     * 订单 -- 取消退款
     */
    @FormUrlEncoded
    @POST("/api/orders/cancelOrderRefund")
    suspend fun cancelOrderRefund(@Field("data") data: String): BaseResponse<Any?>

    /**
     * 订单 -- 查看退款订单详情
     */
    @FormUrlEncoded
    @POST("/api/orders/refundOrderInfo")
    suspend fun getRefundOrder(@Field("data") data: String): BaseResponse<RefundOrderBean?>

    /**
     * 首页二级页面  高佣榜单
     */
    @FormUrlEncoded
    @POST("/api/index/getShowGoods")
    suspend fun gaoyongTuijian(@Field("data") data: String): BaseResponse<List<GaoYongBean>?>


    /**
     * 首页二级页面  每周精选
     */
    @FormUrlEncoded
    @POST("/api/index/getShowGoods")
    suspend fun jingXuanTuijian(@Field("data") data: String): BaseResponse<List<JingXuanBean>?>

    /**
     * 首页二级页面  健康防疫
     */
    @FormUrlEncoded
    @POST("/api/index/getShowGoods")
    suspend fun fangYiTuijian(@Field("data") data: String): BaseResponse<List<FangYiBean>?>

    /**
     * 首页二级页面  好课推荐
     */
    @FormUrlEncoded
    @POST("/api/index/getShowGoods")
    suspend fun haoKeTuijian(@Field("data") data: String): BaseResponse<List<HaoKetjBean>?>

    /**
     * 首页二级页面  品牌馆
     */
    @FormUrlEncoded
    @POST("/api/index/getShowGoods")
    suspend fun pinPaiTuijian(@Field("data") data: String): BaseResponse<List<PinPaiBean>?>


}