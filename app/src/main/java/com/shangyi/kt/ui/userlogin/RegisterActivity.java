package com.shangyi.kt.ui.userlogin;

import android.content.Intent;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.sdxxtop.base.BaseKTActivity;
import com.sdxxtop.base.utils.UIUtils;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityRegisterBinding;
import com.shangyi.business.utils.CheckUtil;
import com.shangyi.business.utils.TimerUtil;
import com.shangyi.business.utils.Utils;
import com.shangyi.kt.ui.userlogin.model.LoginModel;

import org.jetbrains.annotations.NotNull;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseKTActivity<ActivityRegisterBinding, LoginModel> implements View.OnClickListener {

    private Button mBtnRegister;
    private TextView mRegisterTitle;
    private TextView mBtnGoLogin;
    private TextView mBtnYzm;
    private TextView mEtPhone;
    private EditText mRegisterCode;
    private LoginModel mLoginModel;
    private TimerUtil timerUtil;


    /**
     * 获取验证码
     */
    private void getSMSCode() {
        final String phone = Utils.getString(mEtPhone);
        if (!CheckUtil.checkPhone(phone)) {
            return;
        }
        timerUtil.start();
        //获取验证码
        mLoginModel.getCode(phone, 1);
    }

    @Override
    protected void onDestroy() {
        mLoginModel = null;
        super.onDestroy();
    }

    /**
     * 注册成功的回掉
     */


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

    }

    @Override
    public int layoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        mLoginModel = new LoginModel();

        mRegisterTitle = findViewById(R.id.register_title);
        mBtnRegister = findViewById(R.id.btn_register);
        mBtnGoLogin = findViewById(R.id.btn_gologin);
        mBtnYzm = findViewById(R.id.btn_yzm);
        mEtPhone = findViewById(R.id.et_phone);
        mRegisterCode = findViewById(R.id.register_code);

        //字体加粗
        mRegisterTitle.getPaint().setFakeBoldText(true);
        mBtnRegister.setOnClickListener(this);
        mBtnGoLogin.setOnClickListener(this);
        mBtnYzm.setOnClickListener(this);

        timerUtil = new TimerUtil(mBtnYzm);

        InputFilter[] filters = {new InputFilter.LengthFilter(11)};
        mEtPhone.setFilters(filters);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
//                initRegister();
                skipSetPwd();
                break;
            case R.id.btn_gologin:
                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_yzm:
                //获取验证码
                getSMSCode();
                break;
            default:
                //nothing
                break;
        }
    }

    /**
     * 跳转设置密码页面
     */
    private void skipSetPwd() {
        String registerphone = mEtPhone.getText().toString().trim();
        String registerCode = mRegisterCode.getText().toString().trim();

        if (TextUtils.isEmpty(registerphone) && registerphone.length() != 11) {
            UIUtils.showToast("请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(registerCode) && registerphone.length() != 4) {
            UIUtils.showToast("请输入正确的验证码");
            return;
        }
        Intent intent = new Intent(RegisterActivity.this, SetPwdActivity.class);
        intent.putExtra("registerphone",registerphone);
        intent.putExtra("registerCode",registerCode);

        startActivity(intent);
    }
}
