package com.shangyi.kt.ui.mine.mine_vip.adapter

import android.content.Intent
import android.text.Spannable
import android.text.SpannableString
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.mine.bean.YhqItemBean
import com.shangyi.kt.ui.mine.mine_vip.YhqGoodsActivity
import com.shangyi.kt.ui.mine.mine_vip.YhqItemFragment
import com.shangyi.kt.ui.mine.weight.CenteredImageSpan
import kotlinx.android.synthetic.main.item_yhq_view.view.*
import java.lang.Exception
import java.text.SimpleDateFormat


/**
 * Date:2020/5/7
 * author:lwb
 * Desc:
 */
class YhqItemAdapter : BaseQuickAdapter<YhqItemBean?, BaseViewHolder>(R.layout.item_yhq_view) {


    override fun convert(holder: BaseViewHolder, item: YhqItemBean?) {

        holder.itemView.tvTitle.text = item?.shop?.name
        holder.itemView.tvTime.text = "${getFmTime(item?.start_time)}-${getFmTime(item?.end_time)}"
        holder.itemView.glideImageView.loadImage(item?.shop?.shop_avatar ?: "")
        holder.itemView.tvPriceDesc.text = item?.sign
        holder.itemView.tvPrice.text = "${item?.price?.toInt() ?: 0}"
        var span: CenteredImageSpan? = null
        when (item?.type) {
            1 -> {
                span = CenteredImageSpan(context, R.drawable.icon_dpyh_logo)
                holder.itemView.leftLayout.setBackgroundResource(R.drawable.icon_yhq_dpyh_bg)
                holder.itemView.rightLayout.setBackgroundResource(R.drawable.shape_right_layout_dpyh_bg)
            }
            2 -> { // 代金券
                span = CenteredImageSpan(context, R.drawable.icon_djq_logo)
                holder.itemView.leftLayout.setBackgroundResource(R.drawable.icon_yhq_djq_bg)
                holder.itemView.rightLayout.setBackgroundResource(R.drawable.shape_right_layout_djq_bg)
            }
            3 -> {  // 兑换券
                span = CenteredImageSpan(context, R.drawable.icon_dhq_logo)
                holder.itemView.leftLayout.setBackgroundResource(R.drawable.icon_yhq_dhq_bg)
                holder.itemView.rightLayout.setBackgroundResource(R.drawable.shape_right_layout_dhq_bg)
            }
        }
        if (span == null) return
        val spannableString = SpannableString("图片标签 ${item?.name}")
        spannableString.setSpan(span, 0, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        holder.itemView.text.text = spannableString

        holder.itemView.btnDelete.setOnClickListener {
            mListener?.delItem(item?.receive_id)
        }

        holder.itemView.goUse.setOnClickListener {
            var intent = Intent(context, YhqGoodsActivity::class.java)
            intent.putExtra("yhqId", item?.id)
            context.startActivity(intent)
        }
    }

    fun getFmTime(time: String?) = try {
        if (time.isNullOrEmpty()) {
            ""
        }
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val format2 = SimpleDateFormat("yyyy-MM-dd")
        val parse = format.parse(time)
        format2.format(parse!!)
    } catch (e: Exception) {
        ""
    }

    interface OnItemChildClick {
        fun delItem(id: Int?)
    }

    private var mListener: OnItemChildClick? = null

    fun setOnItemChildClick(listener: OnItemChildClick) {
        mListener = listener
    }
}