package com.shangyi.kt.ui.goods.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.shangyi.business.R
import com.shangyi.kt.ui.goods.bean.AppraiseInfoBean
import com.shangyi.kt.ui.goods.bean.CommentImgBean
import kotlinx.android.synthetic.main.item_only_img.view.*
import kotlinx.android.synthetic.main.item_shopinfo_pj.view.*

/**
 * Date:2020/4/14
 * author:lwb
 * Desc:
 */
class PjAdaper : BaseQuickAdapter<AppraiseInfoBean, BaseViewHolder>(R.layout.item_shopinfo_pj) {
    override fun convert(holder: BaseViewHolder, item: AppraiseInfoBean) {
        holder.itemView.ivUserPhoto.loadImage(item.user?.avatar, R.color.placeholder_color)
        holder.itemView.tvUserName.text = item.user?.nickname
        holder.itemView.tvPjContent.text = item.content

        holder.itemView.imgRecycler.layoutManager = GridLayoutManager(context, 3)
        val list = ArrayList<CommentImgBean?>()
        if (item.comment_img != null && item.comment_img.isNotEmpty()) {
            item.comment_img.forEachIndexed { index, commentImgBean ->
                if (index <= 2) {
                    list.add(commentImgBean)
                }
            }
        }
        holder.itemView.imgRecycler.adapter = PjImgAdaper(list)
    }
}


class PjImgAdaper constructor(data: List<CommentImgBean?>?) : BaseQuickAdapter<CommentImgBean?, BaseViewHolder>(R.layout.item_only_img, ArrayList<CommentImgBean?>(data)) {
    override fun convert(holder: BaseViewHolder, item: CommentImgBean?) {
        if (holder.layoutPosition > 2) return
        holder.itemView.glideImageView.loadImage(item?.url ?: "", R.color.placeholder_color)
    }

}