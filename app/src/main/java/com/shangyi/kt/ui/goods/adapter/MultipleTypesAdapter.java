package com.shangyi.kt.ui.goods.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.shangyi.business.R;
import com.shangyi.business.utils.ViewUtil;
import com.shangyi.kt.ui.goods.adapter.viewholder.ImageHolder;
import com.shangyi.kt.ui.goods.adapter.viewholder.TitleHolder;
import com.shangyi.kt.ui.goods.adapter.viewholder.VideoHolder;
import com.shangyi.kt.ui.goods.bean.GoodDetailTopBarBean;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;


/**
 * 自定义布局,多个不同UI切换
 */
public class MultipleTypesAdapter extends BannerAdapter<GoodDetailTopBarBean, RecyclerView.ViewHolder> {
    private Context context;

    public MultipleTypesAdapter(Context context, List<GoodDetailTopBarBean> mDatas) {
        super(mDatas);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new ImageHolder(BannerUtils.getView(parent, R.layout.banner_image));
            case 2:
                return new VideoHolder(BannerUtils.getView(parent, R.layout.banner_video));
            case 3:
                return new TitleHolder(BannerUtils.getView(parent, R.layout.banner_title));
        }
        return new ImageHolder(BannerUtils.getView(parent, R.layout.banner_image));
    }

    @Override
    public int getItemViewType(int position) {
        return getData(getRealPosition(position)).getViewType();
    }

    @Override
    public void onBindView(RecyclerView.ViewHolder holder, GoodDetailTopBarBean data, int position, int size) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case 1:
                ImageHolder imageHolder = (ImageHolder) holder;
//                imageHolder.imageView.setImageResource(data.getImageRes());
                imageHolder.imageView.loadImage(data.getImageUrl(),R.color.placeholder_color);
                break;
            case 2:
//                VideoHolder videoHolder = (VideoHolder) holder;
//                videoHolder.player.setUp(data.getImageUrl(), true, null);
//                videoHolder.player.getBackButton().setVisibility(View.GONE);
//                //增加封面
//                ImageView imageView = new ImageView(context);
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView.setImageResource(R.drawable.image4);
//                videoHolder.player.setThumbImageView(imageView);
//                videoHolder.player.startPlayLogic();
                break;
            case 3:
                TitleHolder titleHolder = (TitleHolder) holder;
                titleHolder.title.setText(data.getTitle());
                titleHolder.title.setBackgroundColor(Color.parseColor(ViewUtil.getRandColor()));
                break;
        }
    }

    @Override
    public void setDatas(List<GoodDetailTopBarBean> datas) {
        super.setDatas(datas);
        notifyDataSetChanged();
    }
}
