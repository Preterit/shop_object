package com.shangyi.kt.ui.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityHomeJingXuanBinding;
import com.shangyi.kt.ui.home.adapter.JingXuanAdapter;
import com.shangyi.kt.ui.home.bean.JingXuanBean;
import com.shangyi.kt.ui.home.model.HomeJingXuanModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeJingXuanActivity extends BaseKTActivity<ActivityHomeJingXuanBinding, HomeJingXuanModel> {

    private RecyclerView mJingxuanRcl;
    private JingXuanAdapter mJingXuanAdapter;

    @NotNull
    @Override
    public Class<HomeJingXuanModel> vmClazz() {
        return HomeJingXuanModel.class;
    }

    @Override
    public void bindVM() {
        getMBinding().setVm(getMViewModel());
    }

    @Override
    public void initObserve() {
        getMBinding().getVm().getSuccessData().observe(this, new Observer<List<JingXuanBean>>() {
            @Override
            public void onChanged(List<JingXuanBean> jingXuanBeans) {
                mJingXuanAdapter.setList(jingXuanBeans);
            }
        });
    }


    @Override
    public int layoutId() {
        return R.layout.activity_home_jing_xuan;
    }

    @Override
    public void initView() {

        mJingxuanRcl = findViewById(R.id.jingxuan_recyclerview);

        mJingXuanAdapter = new JingXuanAdapter();
        mJingxuanRcl.setLayoutManager(new LinearLayoutManager(HomeJingXuanActivity.this, LinearLayoutManager.VERTICAL,false));
        mJingxuanRcl.setAdapter(mJingXuanAdapter);

        View headView = LayoutInflater.from(this).inflate(R.layout.jingxuan_view, null);
        mJingXuanAdapter.addHeaderView(headView);

    }

    @Override
    public void initData() {
        super.initData();
        getMBinding().getVm().jingXuanTuijian("002");

    }
}
