package com.shangyi.kt.ui.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityHuiYuanBinding;

import org.jetbrains.annotations.NotNull;

/**
 * 会员信息
 */
public class HuiYuanActivity extends BaseKTActivity<ActivityHuiYuanBinding,HuiYuanModel> {

    @NotNull
    @Override
    public Class<HuiYuanModel> vmClazz() {
        return HuiYuanModel.class;
    }

    @Override
    public void bindVM() {

    }

    @Override
    public void initObserve() {

    }

    @Override
    public int layoutId() {
        return R.layout.activity_hui_yuan;
    }

    @Override
    public void initView() {

    }
}
