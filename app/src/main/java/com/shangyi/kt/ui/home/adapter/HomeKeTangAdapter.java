package com.shangyi.kt.ui.home.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shangyi.business.R;
import com.shangyi.kt.ui.home.bean.HaoKetjBean;
import com.study.glidemodel.GlideImageView;

import org.jetbrains.annotations.NotNull;


/**
 * data: 2020/5/12 13:24
 * auther: Dz
 * function:
 */
public class HomeKeTangAdapter extends BaseQuickAdapter<HaoKetjBean, BaseViewHolder> {

    public HomeKeTangAdapter() {
        super(R.layout.home_ketang_adapter_view);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, HaoKetjBean item) {
        if (holder.getLayoutPosition() == 0){
            holder.getView(R.id.top_line).setVisibility(View.VISIBLE);
        }else{
            holder.getView(R.id.top_line).setVisibility(View.GONE);
        }

        /*String imgUrl = item.getGoods_one_img().getUrl();
        GlideImageView ivImg = holder.getView(R.id.glideImageView);
        holder.setText(R.id.tvTitle, item.getName());
        holder.setText(R.id.tvPrice, item.getPrice() + "");
        holder.setText(R.id.tvZhuanTx,  item.getSale_price() + "");

        ivImg.loadImage(imgUrl, R.color.placeholder_color);*/


    }
}
