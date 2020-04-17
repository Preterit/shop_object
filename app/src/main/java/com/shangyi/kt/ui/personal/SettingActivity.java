package com.shangyi.kt.ui.personal;


import android.content.Intent;
import android.view.View;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivitySettingBinding;
import com.shangyi.business.weight.CumSettingItemView;

import org.jetbrains.annotations.NotNull;

/**
 * 设置页面
 */
public class SettingActivity extends BaseKTActivity<ActivitySettingBinding,SettingModel>{

    private CumSettingItemView mBackPwd;

    @NotNull
    @Override
    public Class<SettingModel> vmClazz() {
        return SettingModel.class;
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
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {

        mBackPwd = findViewById(R.id.backpwd);
        mBackPwd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backpwd://账号安全
                Intent intent1 = new Intent(SettingActivity.this, BackPwdActivity.class);
                startActivity(intent1);
                break;
            default:
                //nothing
                break;
        }
    }
}
