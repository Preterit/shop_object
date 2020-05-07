package com.shangyi.kt.ui.setting;


import android.content.Intent;
import android.view.View;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivitySettingBinding;
import com.shangyi.business.weight.CumSettingItemView;
import com.shangyi.kt.ui.mine.order.TxActivity;

import org.jetbrains.annotations.NotNull;

/**
 * 设置页面
 */
public class SettingActivity extends BaseKTActivity<ActivitySettingBinding,SettingModel>{

    private CumSettingItemView mBackPwd;
    private CumSettingItemView mSettingAbout;
    private CumSettingItemView mSettingMessage;
    private CumSettingItemView mHuiYuan;

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
        mSettingAbout = findViewById(R.id.setting_about);
        mSettingMessage = findViewById(R.id.setting_message);
        mHuiYuan = findViewById(R.id.huiyuan);

        mBackPwd.setOnClickListener(this);
        mSettingAbout.setOnClickListener(this);
        mSettingMessage.setOnClickListener(this);
        mHuiYuan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backpwd://账号安全
                Intent intent = new Intent(SettingActivity.this, BackPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_message:
                Intent intentMessage = new Intent(SettingActivity.this,SettingMessageActivity.class);
                startActivity(intentMessage);
                break;
            case R.id.setting_about://关于上医宝库
                Intent intent1 = new Intent(SettingActivity.this, AboutSyActivity.class);
                startActivity(intent1);
                break;
            case R.id.huiyuan://会员中心
                Intent intentHuiYan = new Intent(SettingActivity.this, TxActivity.class);
                startActivity(intentHuiYan);
                break;
            default:
                //nothing
                break;
        }
    }
}
