package com.shangyi.kt.ui.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityHomeJkfyBinding;
import com.shangyi.kt.ui.home.activity.HomeJingXuanActivity;
import com.shangyi.kt.ui.home.adapter.JingKangFYAdapter;
import com.shangyi.kt.ui.home.adapter.JingXuanAdapter;
import com.shangyi.kt.ui.home.bean.FangYiBean;
import com.shangyi.kt.ui.home.bean.GaoYongBean;
import com.shangyi.kt.ui.home.model.HomeJianKangFYModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeJkfyActivity extends BaseKTActivity<ActivityHomeJkfyBinding, HomeJianKangFYModel> {

    private RecyclerView mFangYiRcl;
    private JingKangFYAdapter mJingKangFYAdapter;

    @NotNull
    @Override
    public Class<HomeJianKangFYModel> vmClazz() {
        return HomeJianKangFYModel.class;
    }

    @Override
    public void bindVM() {
        getMBinding().setVm(getMViewModel());
    }

    @Override
    public void initObserve() {
        getMBinding().getVm().getSuccessData().observe(this, new Observer<List<FangYiBean>>() {
            @Override
            public void onChanged(List<FangYiBean> fangYiBeans) {
                mJingKangFYAdapter.setList(fangYiBeans);
            }
        });
    }

    @Override
    public int layoutId() {
        return R.layout.activity_home_jkfy;
    }

    @Override
    public void initView() {
        mFangYiRcl = findViewById(R.id.fangyi_recyclerview);

        mJingKangFYAdapter = new JingKangFYAdapter();
        mFangYiRcl.setLayoutManager(new LinearLayoutManager(HomeJkfyActivity.this, LinearLayoutManager.VERTICAL,false));
        mFangYiRcl.setAdapter(mJingKangFYAdapter);

        View headView = LayoutInflater.from(this).inflate(R.layout.jiankangfangyi_view, null);
        mJingKangFYAdapter.addHeaderView(headView);

    }

    @Override
    public void initData() {
        super.initData();
        getMBinding().getVm().FangYiTuijian("003");

    }
}
