package com.shangyi.business.user.login;

import android.content.Intent;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.sdxxtop.base.BaseKTActivity;
import com.sdxxtop.base.utils.UIUtils;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityYzmBinding;
import com.shangyi.business.ui.MainActivity;
import com.shangyi.business.utils.TimerUtil;
import com.shangyi.kt.LiillActivity;

import org.jetbrains.annotations.NotNull;

/**
 * 验证码登录
 */
public class YzmActivity extends BaseKTActivity<ActivityYzmBinding, LoginModel> {


    private TextView mTvRegister;
    private TextView mTvGologin;
    private TextView btnYzm;
    private TextView mYzmXieyi;
    private EditText mEtPhone;
    private EditText mEtCode;
    private Button mBtnYzmlogin;
    private TimerUtil timerUtil;

    @NotNull
    @Override
    public Class<LoginModel> vmClazz() {
        return LoginModel.class;
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
                    UIUtils.showToast("登陆成功");
                    startActivity(new Intent(YzmActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    public int layoutId() {
        return R.layout.activity_yzm;
    }

    @Override
    public void initView() {
        mTvRegister = findViewById(R.id.tv_goregist);
        mBtnYzmlogin = findViewById(R.id.btn_yzmlogin);
        mTvGologin = findViewById(R.id.tv_gologin);
        mEtPhone = findViewById(R.id.et_phone);
        btnYzm = findViewById(R.id.btn_yzm);
        mEtCode = findViewById(R.id.etCode);
        mYzmXieyi = findViewById(R.id.yzm_btn_userxieyi);
        mTvRegister.setOnClickListener(this);
        mTvGologin.setOnClickListener(this);
        btnYzm.setOnClickListener(this);
        mYzmXieyi.setOnClickListener(this);
        mBtnYzmlogin.setOnClickListener(this);

        InputFilter[] filters = {new InputFilter.LengthFilter(11)};
        mEtPhone.setFilters(filters);

        timerUtil = new TimerUtil(btnYzm);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_goregist://立即注册
                Intent intent = new Intent(YzmActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_gologin://密码登录
                finish();
                break;
            case R.id.btn_yzm://获取验证码
                getCode();
                break;
            case R.id.yzm_btn_userxieyi:
                Intent intent3 = new Intent(YzmActivity.this, LiillActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_yzmlogin:
                login();
                break;
            default:
                //nothing
                break;
        }
    }

    /**
     * 登陆
     */
    private void login() {
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            UIUtils.showToast("请先填写手机号");
            return;
        }
        String code = mEtCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            UIUtils.showToast("请先填写验证码");
            return;
        }
        getMBinding().getVm().login(phone, "", code, 2);
    }

    /**
     * 获取验证码
     */
    private void getCode() {
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            UIUtils.showToast("请先填写手机号");
            return;
        }
        timerUtil.start();
        getMBinding().getVm().getCode(phone, 2);
    }
}
