package com.shangyi.kt.ui.home.activity;


import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityHomeJkclassroomViewBinding;
import com.shangyi.kt.ui.home.model.HomeJKClassroomModel;

import org.jetbrains.annotations.NotNull;

public class HomeJKClassroomActivity extends BaseKTActivity<ActivityHomeJkclassroomViewBinding, HomeJKClassroomModel> {

    @NotNull
    @Override
    public Class<HomeJKClassroomModel> vmClazz() {
        return HomeJKClassroomModel.class;
    }

    @Override
    public void bindVM() {

    }

    @Override
    public void initObserve() {

    }

    @Override
    public int layoutId() {
        return R.layout.activity_home_jkclassroom_view;
    }

    @Override
    public void initView() {

    }
}
