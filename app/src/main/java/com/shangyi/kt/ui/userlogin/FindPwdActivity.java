package com.shangyi.kt.ui.userlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sdxxtop.base.BaseKTActivity;
import com.sdxxtop.base.utils.UIUtils;
import com.sdxxtop.webview.utils.WebConstants;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityFindPwdBinding;
import com.shangyi.business.utils.CheckUtil;
import com.shangyi.business.utils.TimerUtil;
import com.shangyi.business.utils.Utils;
import com.shangyi.kt.ui.WebActivity;
import com.shangyi.kt.ui.userlogin.model.LoginModel;

import org.jetbrains.annotations.NotNull;

/**
 * 找回密码
 */
public class FindPwdActivity extends BaseKTActivity<ActivityFindPwdBinding, LoginModel> implements View.OnClickListener {

    private TextView mRegisterTitle;
    private TextView mBtnYzm;
    private TextView mEtPhone;
    private EditText mRegisterCode;
    private TextView mBtnUserXiewyi;
    private LoginModel mLoginModel;
    private TimerUtil timerUtil;
    private Button mBtnFindPwd;
    private EditText mEtSetPwd;
    private EditText mEtSetPwds;


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
        getMBinding().getVm().getFindPwdSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    finish();
                }
            }
        });
    }

    @Override
    public int layoutId() {
        return R.layout.activity_find_pwd;
    }

    @Override
    public void initView() {

        mLoginModel = new LoginModel();

        mRegisterTitle = findViewById(R.id.register_title);
        mBtnYzm = findViewById(R.id.btn_yzm);
        mEtPhone = findViewById(R.id.et_phone);
        mRegisterCode = findViewById(R.id.register_code);
        mBtnUserXiewyi = findViewById(R.id.btn_userxieyi);
        mBtnFindPwd = findViewById(R.id.btn_findpwd);
        mEtSetPwd = findViewById(R.id.et_setpwd);
        mEtSetPwds = findViewById(R.id.et_setpwds);

        //字体加粗
        mRegisterTitle.getPaint().setFakeBoldText(true);
        mBtnYzm.setOnClickListener(this);
        mBtnUserXiewyi.setOnClickListener(this);
        mBtnFindPwd.setOnClickListener(this);

        timerUtil = new TimerUtil(mBtnYzm);

        InputFilter[] filters = {new InputFilter.LengthFilter(11)};
        mEtPhone.setFilters(filters);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yzm:
                //获取验证码
                getSMSCode();
                break;
            case R.id.btn_userxieyi://跳转用户协议、隐私政策：http://39.106.156.132/privacy.html
                WebActivity.startCommonWeb(FindPwdActivity.this, "上医宝库用户协议", "http://39.106.156.132/service.html", WebConstants.LEVEL_BASE);
                break;
            case R.id.btn_findpwd://确认修改密码
                FindPwd();
                break;
            default:
                //nothing
                break;
        }
    }

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

    /**
     * 确认修改密码
     */
    private void FindPwd() {
        String findPwdphone = mEtPhone.getText().toString().trim();
        String findPwdCode = mRegisterCode.getText().toString().trim();
        String setPwd = mEtSetPwd.getText().toString().trim();
        String setPwds = mEtSetPwds.getText().toString().trim();


        if (TextUtils.isEmpty(findPwdphone) && findPwdphone.length() != 11) {
            UIUtils.showToast("请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(findPwdCode) && findPwdCode.length() != 4) {
            UIUtils.showToast("请输入正确的验证码");
            return;
        }

        getMBinding().getVm().findPwd(findPwdphone, findPwdCode, setPwd, setPwds);
    }


    @Override
    protected void onDestroy() {
        mLoginModel = null;
        super.onDestroy();
    }
}
