package com.shangyi.kt.ui.home.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shangyi.business.R;
import com.shangyi.kt.ui.goods.GoodsDetailActivity;
import com.shangyi.kt.ui.home.bean.FangYiBean;
import com.shangyi.kt.ui.home.bean.GaoYongBean;
import com.study.glidemodel.GlideImageView;

import org.jetbrains.annotations.NotNull;


/**
 * data: 2020/5/12 13:24
 * auther: Dz
 * function:
 */
public class JingKangFYAdapter extends BaseQuickAdapter<FangYiBean, BaseViewHolder> {

    public JingKangFYAdapter() {
        super(R.layout.jiankangfangyi_adapter_view);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, FangYiBean item) {
        String imgUrl = item.getGoods_one_img().getUrl();
        GlideImageView ivImg = holder.getView(R.id.glideImageView);
        holder.setText(R.id.tvTitle, item.getName());
        holder.setText(R.id.tvPrice, item.getPrice() + "");
        holder.setText(R.id.tvYishouTx, "已售"+item.getSale_count()+"件");
        holder.setText(R.id.tvZhuanTx,   item.getSale_price() + "");

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
