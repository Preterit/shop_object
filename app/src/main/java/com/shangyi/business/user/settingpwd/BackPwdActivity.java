package com.shangyi.business.user.settingpwd;

import android.view.View;
import android.widget.TextView;

import com.shangyi.business.R;
import com.shangyi.business.base.BaseMVPActivity;

/**
 * 找回密码页面
 */
public class BackPwdActivity extends BaseMVPActivity<BackPwdInterface,BackPwdPresenter> implements BackPwdInterface, View.OnClickListener  {


    private TextView mBackpwdTitle;
    private TextView mBtnYzm;

    @Override
    protected void initView() {

        mBackpwdTitle = findViewById(R.id.backpwd_title);
        mBtnYzm = findViewById(R.id.btn_yzm);

        mBackpwdTitle.getPaint().setFakeBoldText(true);
        mBtnYzm.getPaint().setFakeBoldText(true);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int setView() {
        return R.layout.activity_back;
    }

    @Override
    protected BackPwdPresenter initPresenter() {
        return new BackPwdPresenter();
    }

    @Override
    public void onClick(View v) {

    }
}
