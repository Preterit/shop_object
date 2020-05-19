package com.shangyi.kt.ui.home.activity;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityHomeGaoyongBinding;
import com.shangyi.kt.ui.home.adapter.GaoYongAdapter;
import com.shangyi.kt.ui.home.bean.GaoYongBean;
import com.shangyi.kt.ui.home.model.HomeGaoyongModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeGaoyongActivity extends BaseKTActivity<ActivityHomeGaoyongBinding, HomeGaoyongModel> {

    private RecyclerView mGaoYongRcy;
    private GaoYongAdapter mGaoYongAdapter;


    @NotNull
    @Override
    public Class<HomeGaoyongModel> vmClazz() {
        return HomeGaoyongModel.class;
    }

    @Override
    public void bindVM() {
        getMBinding().setVm(getMViewModel());
    }

    @Override
    public void initObserve() {
        getMBinding().getVm().getSuccessData().observe(this, new Observer<List<GaoYongBean>>() {
            @Override
            public void onChanged(List<GaoYongBean> gaoYongBeans) {
                mGaoYongAdapter.setList(gaoYongBeans);
            }
        });
    }

    @Override
    public int layoutId() {
        return R.layout.activity_home_gaoyong;
    }

    @Override
    public void initView() {

        mGaoYongRcy = findViewById(R.id.gaoyong_recyclerview);

        mGaoYongAdapter = new GaoYongAdapter();
        mGaoYongRcy.setLayoutManager(new LinearLayoutManager(HomeGaoyongActivity.this, LinearLayoutManager.VERTICAL,false));
        mGaoYongRcy.setAdapter(mGaoYongAdapter);

        View headView = LayoutInflater.from(this).inflate(R.layout.gaoyong_view, null);
        mGaoYongAdapter.addHeaderView(headView);

    }

    @Override
    public void initData() {
        super.initData();
        getMBinding().getVm().gaoYongTuijian("001");
    }

    @Override
    public void onClick(@NotNull View v) {
        switch (v.getId()){
            default:
                //nothing
                break;
        }
    }
}
