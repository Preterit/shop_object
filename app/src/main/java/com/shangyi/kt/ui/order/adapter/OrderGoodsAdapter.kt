package com.shangyi.kt.ui.order.adapter

import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.fragment.car.entity.CommitOrderBean
import com.shangyi.kt.ui.order.weight.OrderGoodsItemView
import kotlinx.android.synthetic.main.item_order_goods_view.view.*

/**
 * Date:2020/4/24
 * author:lwb
 * Desc:
 */
class OrderGoodsAdapter(data: ArrayList<CommitOrderBean>?) : BaseQuickAdapter<CommitOrderBean, BaseViewHolder>(R.layout.item_order_goods_view, data) {

    override fun convert(holder: BaseViewHolder, item: CommitOrderBean) {
        holder.itemView.tvShopName.text = item.shopName
        holder.itemView.ivShopPhoto.loadImage(item.shopImg ?: "", R.color.placeholder_color)
        holder.itemView.tvTotalPrice.text = "¥ ${item.totalPrice}"
        holder.itemView.tvFanPrice.text = "¥ ${item.fanPrice}"

        holder.itemView.goodsLayout.removeAllViews()
        item.goodsInfos?.forEach {
            var orderGoodsItemView = OrderGoodsItemView(context)
            orderGoodsItemView.setData(it)
            holder.itemView.goodsLayout.addView(orderGoodsItemView)
        }

        holder.itemView.editPs.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                item.psText = s.toString().trim()
            }

        })
    }
}