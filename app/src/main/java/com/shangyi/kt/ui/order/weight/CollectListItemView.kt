package com.shangyi.kt.ui.order.weight

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.shangyi.business.R
import com.shangyi.kt.ui.mine.bean.Good
import kotlinx.android.synthetic.main.item_collect_list_view.view.*
import kotlinx.android.synthetic.main.item_collect_list_view.view.checkboxLayout
import kotlinx.android.synthetic.main.item_collect_view.view.*

/**
 * Date:2020/4/24
 * author:lwb
 * Desc:
 */
class CollectListItemView : LinearLayout {
    constructor(context: Context) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.item_collect_list_view, this)
    }

    fun setData(data: Good?) {
        glideImageView.loadImage(data?.url ?: "")
        tvGoodsName.text = data?.name
        tvPrice.text = data?.sale_price.toString()
        tvZhuanTx.text = "赚¥ ${data?.normal_dealer}"

        if (data?.is_show == 1) {
            // (1、展示 0、不展示）
            tvNoGoods.visibility = View.VISIBLE
            glideImageView.alpha = 0.7f
            tvNoPrice.visibility = View.VISIBLE
            conPriceLayout.visibility = View.INVISIBLE
        } else {
            tvNoGoods.visibility = View.GONE
            tvNoPrice.visibility = View.INVISIBLE
            conPriceLayout.visibility = View.VISIBLE
        }

        checkboxLayout.visibility = if (data?.isEdit!!) View.VISIBLE else View.GONE
        checkbox.isEnabled = data.isSelect

        checkboxLayout.setOnClickListener {
            data.isSelect = !data.isSelect
            checkbox.isEnabled = data.isSelect
        }

        btnDelete.setOnClickListener {
            mListener?.onDelete(data)
        }
    }

    interface OnGoodsDelete {
        fun onDelete(goods: Good)
    }

    private var mListener: OnGoodsDelete? = null

    fun setOnGoodsDeleteListener(listener: OnGoodsDelete) {
        mListener = listener
    }
}