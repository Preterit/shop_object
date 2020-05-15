package com.shangyi.kt.ui.home.activity;


import android.os.Bundle;
import android.view.View;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityHomeJkclassroomViewBinding;
import com.shangyi.kt.ui.home.adapter.HaokeRcyAdapter;
import com.shangyi.kt.ui.home.adapter.HomeKeTangAdapter;
import com.shangyi.kt.ui.home.adapter.PinpaiAdapter;
import com.shangyi.kt.ui.home.bean.HaoKetjBean;
import com.shangyi.kt.ui.home.model.HomeJKClassroomModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeJKClassroomActivity extends BaseKTActivity<ActivityHomeJkclassroomViewBinding,HomeJKClassroomModel> {

    private RecyclerView mKeTangRcy;
    private HomeKeTangAdapter mHomeKeTangAdapter;

    @NotNull
    @Override
    public Class<HomeJKClassroomModel> vmClazz() {
        return HomeJKClassroomModel.class;
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
                mHomeKeTangAdapter.setList(haoKetjBeans);
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
        return R.layout.activity_home_jkclassroom_view;
    }

    @Override
    public void initView() {

        mKeTangRcy = findViewById(R.id.ketang_recyclerview);

        mHomeKeTangAdapter = new HomeKeTangAdapter();
        mKeTangRcy.setLayoutManager(new LinearLayoutManager(HomeJKClassroomActivity.this,LinearLayoutManager.VERTICAL ,false));
        mKeTangRcy.setAdapter(mHomeKeTangAdapter);
    }
}
