package com.shangyi.kt.fragment.car.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Date:2020/4/24
 * author:lwb
 * Desc:
 */
@Parcelize
data class CommitOrderBean(
        val shopId: Int?,
        val shopImg: String?,
        val shopName: String?,
        val goodsInfos: List<GoodsInfoBean>?,
        val fanPrice: Float = 0f,
        var psText: String?,
        val totalPrice: Float = 0f
) : Parcelable


@Parcelize
data class GoodsInfoBean(
        var goodsId: Int,
        val goodsCount: Int,
        val goodsPrice: Float,
        val goodsName: String,
        val SpecStr: String,
        var goodsImg: String?,
        val goodsSpecId: Int
) : Parcelable
