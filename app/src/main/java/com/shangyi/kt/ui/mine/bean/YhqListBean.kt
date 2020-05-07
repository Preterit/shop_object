package com.shangyi.kt.ui.mine.bean

/**
 * Date:2020/5/7
 * author:lwb
 * Desc:
 */
data class YhqListBean(
        val `data`: List<YhqItemBean>,
        val number: List<Number>
)

data class Number(
        val id: Int,
        val name: String,
        val number: Int
)

data class YhqItemBean(
        val id: Int,//优惠券id
        val receive_id: Int,//领取的优惠券id
        val price: Float,//优惠券价格
        val name: String,//仅限购买商品使用
        val start_time: String, //使用开始时间
        val end_time: String, //使用结束时间
        val sign: String, //说明
        val shop: Shop //店铺信息
)

// {
//            "id": 1,            //优惠券id
//            "receive_id": 1,    //领取的优惠券id
//            "price": 20,        //优惠券价格
//            "name": "仅限购买商品使用",        //文字信息
//            "start_time": "2020-04-20 11:04:41",    //使用开始时间
//            "end_time": "2020-04-22 11:04:47",        //使用结束时间
//            "sign": "限定商品使用",                //说明
//            "shop": {                    //店铺信息
//                "name": "系统发放券"        //店铺名称
//                "shop_avatar": null        //店铺头像
//            }
//        }