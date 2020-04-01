package com.shangyi.business.user.settingpwd;

import android.content.Intent;
import android.graphics.Paint;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shangyi.business.R;
import com.shangyi.business.base.BaseMVPActivity;
import com.shangyi.business.user.replenish.ReplenishActivity;

/**
 * 设置密码
 */
public class SettingPwdActivity extends BaseMVPActivity<SettingPwdInterface,SettingPwdPresenter> implements SettingPwdInterface, View.OnClickListener {


    private TextView mSetPwd;
    private TextView mRegisterJump;
    private ImageView mBtnNext;

    @Override
    protected void initView() {

        mSetPwd = findViewById(R.id.setpwd);
        mRegisterJump = findViewById(R.id.register_jump);
        mBtnNext = findViewById(R.id.btn_next);


        //字体加粗
        mSetPwd.getPaint().setFakeBoldText(true);
        //添加下划线
        mRegisterJump.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mBtnNext.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int setView() {
        return R.layout.activity_setting_pwd;
    }

    @Override
    protected SettingPwdPresenter initPresenter() {
        return new SettingPwdPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                Intent intent = new Intent(SettingPwdActivity.this,ReplenishActivity.class);
                startActivity(intent);
                break;
            default:
                //nothing
                break;
        }
    }
}
