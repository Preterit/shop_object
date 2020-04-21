package com.shangyi.kt.ui.pingjia.adapter

import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.pingjia.bean.ContentBean
import kotlinx.android.synthetic.main.item_pingjia_content_view.view.*
import java.text.SimpleDateFormat

/**
 * Date:2020/4/21
 * author:lwb
 * Desc:
 */
class PingjiaContentAdapter : BaseQuickAdapter<ContentBean?, BaseViewHolder>(R.layout.item_pingjia_content_view) {
    override fun convert(holder: BaseViewHolder, item: ContentBean?) {
        if (item == null) {
            return
        }

        holder.itemView.imgRecycler.layoutManager = GridLayoutManager(context, 4)
        val commentImg = item.comment_img as ArrayList
        holder.itemView.imgRecycler.adapter = PinglunImgAdapter(commentImg)

        holder.itemView.tvName.text = item.user?.nickname
        holder.itemView.tvTime.text = if (item.create_time?.isNotEmpty()!!) item.create_time.split(" ")[0] else ""
        holder.itemView.tvContent.text = item.content
    }
}