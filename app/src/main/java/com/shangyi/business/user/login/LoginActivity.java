package com.shangyi.business.user.login;

import android.content.Intent;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sdxxtop.base.utils.UIUtils;
import com.shangyi.business.R;
import com.shangyi.business.api.RetrofitClient;
import com.shangyi.business.base.BaseMVPActivity;
import com.shangyi.business.bean.LoginBean;
import com.shangyi.business.bean.RegisterBean;
import com.shangyi.business.http.AESUtils;
import com.shangyi.business.network.Params;
import com.shangyi.business.ui.goods.GoodsDialog;
import com.shangyi.business.net.APIServer;
import com.shangyi.business.ui.poster.PosterActivity;
import com.shangyi.business.user.register.RegisterActivity;
import com.shangyi.business.user.settingpwd.BackPwdActivity;
import com.shangyi.business.utils.RetrofitUtils;
import com.shangyi.kt.LiillActivity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 登陆界面
 */
public class LoginActivity extends BaseMVPActivity<LoginInterface, LoginPresenter> implements LoginInterface, View.OnClickListener {

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
    protected void initView() {
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

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int setView() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

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

        loginModel.login(phone,pwd);
    }


}
