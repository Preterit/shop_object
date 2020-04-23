package com.shangyi.kt.ui.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shangyi.business.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * data: 2020/4/22 9:51
 * auther: Dz
 * function:确认订单 多商品适配器
 */
public class TypeTwoViewAdapter extends RecyclerView.Adapter {


    private Context mContext;

    public TypeTwoViewAdapter(Context context){
        mContext = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.confirm_order_goods_img_item, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(inflate);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
        Glide.with(mContext).load(R.drawable.success_img).into(imageViewHolder.mOrderGoodsImage);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private class ImageViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mOrderGoodsImage;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            mOrderGoodsImage = itemView.findViewById(R.id.order_goods_img);
        }
    }
}
