package com.shangyi.kt.ui.goods.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.glidemodel.GlideImageView;

public class ImageHolder extends RecyclerView.ViewHolder {
    public GlideImageView imageView;

    public ImageHolder(@NonNull View view) {
        super(view);
        this.imageView = (GlideImageView) view;
    }
}