package com.shangyi.kt.ui.address.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Date:2020/4/21
 * author:lwb
 * Desc:
 */
@Parcelize
data class AreaListBean(
        val city: ProviceBean?,
        val city_id: Int,
        val country: String,
        val county: ProviceBean?,
        val county_id: Int,
        val detail: String,
        val id: Int,
        val is_default: Int,
        val mobile: String,
        val provice: ProviceBean?,
        val province_id: Int,
        val recipient: String,
        val uid: Int
) : Parcelable

@Parcelize
data class ProviceBean(
        val id: Int,
        val name: String
) : Parcelable
