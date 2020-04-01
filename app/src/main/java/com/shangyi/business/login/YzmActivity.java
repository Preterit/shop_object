package com.shangyi.business.login;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.shangyi.business.R;
import com.shangyi.business.base.BaseMVPActivity;
import com.shangyi.business.register.RegisterActivity;

public class YzmActivity extends BaseMVPActivity<YzmInterface,YzmPresenter> implements YzmInterface, View.OnClickListener  {


    private TextView mTvRegister;
    private TextView mTvGologin;

    @Override
    protected void initView() {

        mTvRegister = findViewById(R.id.tv_goregist);
        mTvGologin = findViewById(R.id.tv_gologin);
        mTvRegister.setOnClickListener(this);
        mTvGologin.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int setView() {
        return R.layout.activity_yzm;
    }

    @Override
    protected YzmPresenter initPresenter() {
        return new YzmPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_goregist://立即注册
                Intent intent = new Intent(YzmActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_gologin://密码登录
                Intent intent1 = new Intent(YzmActivity.this,LoginActivity.class);
                startActivity(intent1);
                break;
            default:
                //nothing
                break;
        }
    }
}
