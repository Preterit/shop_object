package com.shangyi.kt.ui.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shangyi.business.R;
import com.shangyi.kt.ui.home.bean.PinPaiBean;
import com.shangyi.kt.ui.order.bean.OrderBean;
import com.study.glidemodel.GlideImageView;

import org.jetbrains.annotations.NotNull;


/**
 * data: 2020/5/12 13:24
 * auther: Dz
 * function:
 */
public class PinpaiAdapter extends BaseQuickAdapter<PinPaiBean, BaseViewHolder> {

    public PinpaiAdapter() {
        super(R.layout.pinpai_adapter_view);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, PinPaiBean item) {
        String imgUrl = item.getGoods_one_img().getUrl();
        GlideImageView ivImg = holder.getView(R.id.glideImageView);
        holder.setText(R.id.tvPrice, item.getPrice() + "");
        holder.setText(R.id.tvZhuanTx, "返" + item.getSale_price() + "");

        ivImg.loadImage(imgUrl, R.color.placeholder_color);


    }
}
