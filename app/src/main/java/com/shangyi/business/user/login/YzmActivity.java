package com.shangyi.business.user.login;

import android.content.Intent;

import android.view.View;
import android.widget.TextView;

import com.shangyi.business.R;
import com.shangyi.business.base.BaseMVPActivity;
import com.shangyi.business.network.Params;
import com.shangyi.business.user.register.RegistPresenter;
import com.shangyi.business.user.register.RegisterActivity;
import com.shangyi.kt.LiillActivity;

/**
 * 验证码登录
 */
public class YzmActivity extends BaseMVPActivity<YzmInterface, YzmPresenter> implements YzmInterface, View.OnClickListener  {


    private TextView mTvRegister;
    private TextView mTvGologin;
    private TextView btnYzm;
    private TextView mYzmXieyi;

    @Override
    protected void initView() {

        mTvRegister = findViewById(R.id.tv_goregist);
        mTvGologin = findViewById(R.id.tv_gologin);
        btnYzm = findViewById(R.id.btn_yzm);
        mYzmXieyi = findViewById(R.id.yzm_btn_userxieyi);
        mTvRegister.setOnClickListener(this);
        mTvGologin.setOnClickListener(this);
        btnYzm.setOnClickListener(this);
        mYzmXieyi.setOnClickListener(this);
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
            case R.id.btn_yzm://获取验证码
                getCode();
                break;
            case R.id.yzm_btn_userxieyi:
                Intent intent3 = new Intent(YzmActivity.this, LiillActivity.class);
                startActivity(intent3);
                break;
            default:
                //nothing
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getCode() {
        Params params = new Params();
        params.put("login_name", "18614005205");
        params.put("send_type", "1");
        String aesData = params.getAESData();

        RegistPresenter registPresenter = new RegistPresenter();
        registPresenter.getCode(aesData);

    }
}
