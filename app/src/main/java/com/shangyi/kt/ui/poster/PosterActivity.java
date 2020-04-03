package com.shangyi.kt.ui.poster;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityPosterBinding;
import com.shangyi.kt.ui.poster.model.PosterModel;

import org.jetbrains.annotations.NotNull;

public class PosterActivity extends BaseKTActivity<ActivityPosterBinding, PosterModel> {

    private PosterAdapter mPosterAdapter;
    private RecyclerView mPosterRecyclerView;

    @NotNull
    @Override
    public Class<PosterModel> vmClazz() {
        return PosterModel.class;
    }

    @Override
    public void bindVM() {
        getMBinding().setVm(getMViewModel());
    }

    @Override
    public void initObserve() {

    }

    @Override
    public int layoutId() {
        return R.layout.activity_poster;
    }

    @Override
    public void initView() {
        mPosterRecyclerView = findViewById(R.id.poster_recyclerview);
        mPosterAdapter = new PosterAdapter();
        mPosterRecyclerView.setLayoutManager(new GridLayoutManager(PosterActivity.this,4));
        mPosterRecyclerView.setAdapter(mPosterAdapter);
    }
}
