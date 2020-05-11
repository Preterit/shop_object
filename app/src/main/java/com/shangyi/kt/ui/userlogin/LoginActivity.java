package com.shangyi.kt.ui.userlogin;

import android.app.Activity;
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
import com.sdxxtop.webview.utils.WebConstants;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityLoginBinding;
import com.shangyi.kt.ui.MainActivity;
import com.shangyi.kt.ui.WebActivity;
import com.shangyi.kt.ui.poster.PosterActivity;
import com.shangyi.kt.ui.userlogin.model.LoginModel;

import org.jetbrains.annotations.NotNull;

/**
 * 登陆界面
 */
public class LoginActivity extends BaseKTActivity<ActivityLoginBinding, LoginModel> implements View.OnClickListener {

    private final String TAG = "LoginActivity";

    private TextView mTvRegister;
    private TextView mTvYzm;
    private TextView mTvBackPwd;
    private Button mBtnLogin;
    private EditText mEtPhone;
    private EditText mEtPwd;
    private TextView mBtnUserxieyi;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                initLogin();
                break;
            case R.id.tv_goregist://立即注册
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_goyzm://验证码登录
                Intent intent1 = new Intent(LoginActivity.this, YzmActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_back_pwd:
                Intent intent2 = new Intent(LoginActivity.this, FindPwdActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_userxieyi:
                WebActivity.startCommonWeb(LoginActivity.this, "上医宝库用户协议", "http://39.106.156.132/service.html", WebConstants.LEVEL_BASE);
                break;
            default:
                //nothing
                break;
        }
    }

    /**
     * 账号密码登录
     */
    private void initLogin() {
        String phone = mEtPhone.getText().toString().trim();
        String pwd = mEtPwd.getText().toString().trim();

        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
            UIUtils.showToast("请填写登陆信息");
            return;
        }

        getMBinding().getVm().login(phone, pwd, "", 1);
    }


    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mTvRegister = findViewById(R.id.tv_goregist);
        mTvYzm = findViewById(R.id.tv_goyzm);
        mTvBackPwd = findViewById(R.id.tv_back_pwd);
        mBtnLogin = findViewById(R.id.btn_login);
        mEtPhone = findViewById(R.id.et_login_phone);
        mEtPwd = findViewById(R.id.et_pwd);
        mBtnUserxieyi = findViewById(R.id.btn_userxieyi);

        mTvRegister.setOnClickListener(this);
        mTvYzm.setOnClickListener(this);
        mTvBackPwd.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnUserxieyi.setOnClickListener(this);
        //字体加粗
        mTvYzm.getPaint().setFakeBoldText(true);

        InputFilter[] filters = {new InputFilter.LengthFilter(11)};
        mEtPhone.setFilters(filters);
    }

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
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}
