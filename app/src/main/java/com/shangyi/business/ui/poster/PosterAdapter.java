package com.shangyi.business.ui.poster;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shangyi.business.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * data: 2020/4/2 13:45
 * auther: Dz
 * function:
 */
class PosterAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int[] imgRes = {R.mipmap.poster_down,R.mipmap.poster_qq,R.mipmap.poster_wechat,R.mipmap.poster_xinlang};
    private String[] strRes = {"下载","QQ","微信","新浪"};

    public PosterAdapter() {
        super(R.layout.recycler_view_item);
        List<String> strings = Arrays.asList(strRes);
        replaceData(strings);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        View view = baseViewHolder.getView(R.id.ivImg);

        baseViewHolder.setText(R.id.tvTitle,strRes[getItemPosition(s)]);
        baseViewHolder.setImageResource(R.id.ivImg,imgRes[getItemPosition(s)]);
    }
}
