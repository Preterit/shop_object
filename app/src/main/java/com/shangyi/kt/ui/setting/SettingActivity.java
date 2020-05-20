package com.shangyi.kt.ui.setting;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivitySettingBinding;
import com.shangyi.business.network.Constants;
import com.shangyi.business.network.SpUtil;
import com.shangyi.business.weight.CumSettingItemView;
import com.shangyi.kt.ui.MainActivity;
import com.shangyi.kt.ui.mine.order.TxActivity;
import com.shangyi.kt.ui.userlogin.LoginActivity;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.Observer;

/**
 * 设置页面
 */
public class SettingActivity extends BaseKTActivity<ActivitySettingBinding,SettingModel>{

    private CumSettingItemView mBackPwd;
    private CumSettingItemView mSettingAbout;
    private CumSettingItemView mSettingMessage;
    private CumSettingItemView mHuiYuan;
    private TextView mQuitLogin;

    public static final String IS_SKIP = "is_Skip";
    private boolean isSkip;   // 是否跳转到主页面 、 详情里面进行登陆操作，不需要跳转到主页面。

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
        getMBinding().getVm().getLoginSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    if (isSkip) {
                        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                        intent.putExtra(MainActivity.IS_LOGIN, 1);
                        startActivity(intent);
                        finish();
                    } else {
                        finish();
                    }
                }
            }
        });
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
        mQuitLogin = findViewById(R.id.quit_login);

        mBackPwd.setOnClickListener(this);
        mSettingAbout.setOnClickListener(this);
        mSettingMessage.setOnClickListener(this);
        mHuiYuan.setOnClickListener(this);
        mQuitLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backpwd://账号安全
                //Intent intent = new Intent(SettingActivity.this, HomeJkfyActivity.class);
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
            case R.id.quit_login://退出登录
                quitLogin();
                break;
            default:
                //nothing
                break;
        }
    }

    /**
     * 退出登录
     */
    private void quitLogin() {
        getMBinding().getVm().quitLogin();
    }
}
