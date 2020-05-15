package com.shangyi.kt.ui.home.activity;

import android.view.LayoutInflater;
import android.view.View;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityHomeHaoKefyBinding;
import com.shangyi.kt.ui.home.adapter.HaokeRcyAdapter;
import com.shangyi.kt.ui.home.bean.HaoKetjBean;
import com.shangyi.kt.ui.home.model.HaokeModel;
import com.shangyi.kt.ui.order.bean.OrderBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeHaoKefyActivity extends BaseKTActivity<ActivityHomeHaoKefyBinding, HaokeModel> {

    private HaokeRcyAdapter mHaokeRcyAdapter;
    private RecyclerView mHaokeRcy;

    @NotNull
    @Override
    public Class<HaokeModel> vmClazz() {
        return HaokeModel.class;
    }

    @Override
    public void bindVM() {
        getMBinding().setVm(getMViewModel());
    }

    @Override
    public void initObserve() {
        getMBinding().getVm().getSuccessData().observe(this, new Observer<List<HaoKetjBean>>() {
            @Override
            public void onChanged(List<HaoKetjBean> haoKetjBeans) {
                mHaokeRcyAdapter.setList(haoKetjBeans);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        getMBinding().getVm().haoKeTuijian("004");
    }

    @Override
    public int layoutId() {
        return R.layout.activity_home_hao_kefy;
    }

    @Override
    public void initView() {

        mHaokeRcy = findViewById(R.id.haoke_recyclerview);
        mHaokeRcyAdapter = new HaokeRcyAdapter();
        mHaokeRcy.setLayoutManager(new LinearLayoutManager(HomeHaoKefyActivity.this, LinearLayoutManager.VERTICAL,false));
        mHaokeRcy.setAdapter(mHaokeRcyAdapter);

        View headView = LayoutInflater.from(this).inflate(R.layout.haoke_view, null);
        mHaokeRcyAdapter.addHeaderView(headView);
    }
}
