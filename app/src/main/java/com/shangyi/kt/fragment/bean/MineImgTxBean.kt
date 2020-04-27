package com.shangyi.kt.fragment.bean

import com.shangyi.business.R

/**
 * Date:2020/4/27
 * author:lwb
 * Desc:
 */
data class MineImgTxBean(val img: Int, val title: String) {

    companion object{
        fun getOrderData() = arrayListOf<MineImgTxBean>(
                MineImgTxBean(R.drawable.icon_ordercenter_dfk, "待付款"),
                MineImgTxBean(R.drawable.icon_ordercenter_dfh, "待发货"),
                MineImgTxBean(R.drawable.icon_ordercenter_dsh, "待收货"),
                MineImgTxBean(R.drawable.icon_ordercenter_dpj, "待评价"),
                MineImgTxBean(R.drawable.icon_ordercenter_tksh, "退款/售后")
        )

        fun getVipData() = arrayListOf<MineImgTxBean>(
                MineImgTxBean(R.drawable.icon_vipcenter_yhq, "优惠券"),
                MineImgTxBean(R.drawable.icon_vipcenter_collect, "收藏"),
                MineImgTxBean(R.drawable.icon_vipcenter_fx_manager, "分销管理"),
                MineImgTxBean(R.drawable.icon_vipcenter_shouyi, "我的收益"),
                MineImgTxBean(R.drawable.icon_vipcenter_address_manager, "地址管理"),
                MineImgTxBean(R.drawable.icon_vipcenter_feedback, "意见反馈"),
                MineImgTxBean(R.drawable.icon_vipcenter_jf_shop, "积分商城")
        )
    }
}

