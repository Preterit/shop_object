package com.shangyi.kt.ui.home.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shangyi.business.R;
import com.shangyi.kt.ui.goods.GoodsDetailActivity;
import com.shangyi.kt.ui.home.bean.GaoYongBean;
import com.shangyi.kt.ui.order.bean.OrderBean;
import com.study.glidemodel.GlideImageView;

import org.jetbrains.annotations.NotNull;


/**
 * data: 2020/5/12 13:24
 * auther: Dz
 * function:
 */
public class GaoYongAdapter extends BaseQuickAdapter<GaoYongBean, BaseViewHolder> {

    public GaoYongAdapter() {
        super(R.layout.gaoyong_adapter_view);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, GaoYongBean item) {
        String imgUrl = item.getGoods_one_img().getUrl();
        //int imgUrl = R.drawable.duck;
        GlideImageView ivImg = holder.getView(R.id.glideImageView);
        holder.setText(R.id.tvTitle, item.getName());
        holder.setText(R.id.tvPrice, item.getPrice() + "");
        holder.setText(R.id.tvZhuanTx, "赚" + item.getSale_price() + "");

        ivImg.loadImage(imgUrl, R.color.placeholder_color);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                intent.putExtra("goodsId",item.getId());
                getContext().startActivity(intent);
            }
        });

    }
}
