package com.shangyi.kt.ui.address.adapter

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.address.AddAddressActivity
import com.shangyi.kt.ui.address.bean.AreaListBean
import kotlinx.android.synthetic.main.item_address_list.view.*

/**
 * Date:2020/4/19
 * author:lwb
 * Desc:
 */
class AddressListAdapter : BaseQuickAdapter<AreaListBean, BaseViewHolder>(R.layout.item_address_list) {
    override fun convert(holder: BaseViewHolder, item: AreaListBean) {
        holder.itemView.tvName.text = item.recipient
        holder.itemView.tvNumber.text = item.mobile
        holder.itemView.tvIsDufault.visibility = if (item.is_default == 0) View.GONE else View.VISIBLE
        holder.itemView.tvAddressStr.text = "${item.provice?.name}${item.city?.name}${item.county?.name}${item.detail}"
        holder.itemView.edit.setOnClickListener {
            val intent = Intent(context, AddAddressActivity::class.java)
            intent.putExtra("areaBean",item)
            context.startActivity(intent)
        }
    }
}