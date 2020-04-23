package com.shangyi.kt.ui.order.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shangyi.business.R;
import com.shangyi.kt.ui.pingjia.OrderBean;
import com.study.glidemodel.GlideImageView;

import org.jetbrains.annotations.NotNull;

/**
 * data: 2020/4/21 10:55
 * auther: Dz
 * function:
 */
public class SuccessOrderTuijianAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {

    public SuccessOrderTuijianAdapter() {
        super(R.layout.item_success_order_view);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, OrderBean item) {
        String imgUrl = item.getGoods_one_img().getUrl();
        GlideImageView ivImg = holder.getView(R.id.glideImageView);
        holder.setText(R.id.tvTitle, item.getName());
        holder.setText(R.id.tvPrice, item.getPrice() + "");
        holder.setText(R.id.tvZhuanTx, "￥" + item.getSale_price() + "");

        ivImg.loadImage(imgUrl, R.color.placeholder_color);

    }
}
