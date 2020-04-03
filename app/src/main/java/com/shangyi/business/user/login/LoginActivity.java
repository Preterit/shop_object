package com.shangyi.business.user.login;

import android.content.Intent;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sdxxtop.base.BaseNormalActivity;
import com.sdxxtop.base.utils.UIUtils;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityLoginBinding;
import com.shangyi.business.ui.goods.GoodsDialog;
import com.shangyi.business.ui.poster.PosterActivity;

/**
 * 登陆界面
 */
public class LoginActivity extends BaseNormalActivity<ActivityLoginBinding> implements View.OnClickListener {

    private final String TAG = "LoginActivity";

    private TextView mTvRegister;
    private TextView mTvYzm;
    private TextView mTvBackPwd;
    private Button mBtnLogin;
    private EditText mEtPhone;
    private EditText mEtPwd;
    private TextView mBtnUserxieyi;
    private LoginModel loginModel = new LoginModel();


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
                Intent intent2 = new Intent(LoginActivity.this, PosterActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_userxieyi:
                GoodsDialog goodsDialog = new GoodsDialog();
                goodsDialog.show(getSupportFragmentManager(), "");
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

        loginModel.login(phone, pwd, "0", 1);
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
        mEtPhone = findViewById(R.id.et_phone);
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
}
