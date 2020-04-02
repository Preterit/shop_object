package com.shangyi.business.user.register;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.shangyi.business.R;
import com.shangyi.business.api.RetrofitClient;
import com.shangyi.business.base.BaseMVPActivity;
import com.shangyi.business.bean.BaseResponse;
import com.shangyi.business.bean.ParmsBean;
import com.shangyi.business.bean.RegisterBean;
import com.shangyi.business.http.RequestCallBack;
import com.shangyi.business.network.Params;
import com.shangyi.business.user.login.LoginActivity;
import com.shangyi.business.net.APIServer;
import com.shangyi.business.user.login.LoginModel;
import com.shangyi.business.user.settingpwd.SettingPwdActivity;
import com.shangyi.business.utils.CheckUtil;
import com.shangyi.business.utils.RequestUtils;
import com.shangyi.business.utils.RetrofitUtils;
import com.shangyi.business.utils.Utils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import sing.util.LogUtil;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseMVPActivity<RegistInterface,RegistPresenter> implements RegistInterface, View.OnClickListener  {


    private final String TAG = RegisterActivity.class.getSimpleName();

    private Button mBtnRegister;
    private TextView mRegisterTitle;
    private TextView mBtnGoLogin;
    private TextView mBtnYzm;
    private TextView mEtPhone;
    private EditText mRegisterCode;
    private LoginModel mLoginModel;

    @Override
    protected void initView() {

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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int setView() {
        return R.layout.activity_register;
    }

    @Override
    protected RegistPresenter initPresenter() {
        return new RegistPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                initRegister();
                Intent intent = new Intent(RegisterActivity.this,SettingPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_gologin:
                Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
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
     * 执行注册逻辑
     */
    private void initRegister() {
        String registerphone = mEtPhone.getText().toString().trim();
        String registerCode = mRegisterCode.getText().toString().trim();

        mLoginModel.register(registerphone,registerCode);
    }


    private String phoneStr;// 收到验证码的手机号，防止收到验证码后修改手机号

    private String code = "";// 接口的验证码
    /**
     * 获取验证码
     */
    private void getSMSCode() {

        final String phone = Utils.getString(mEtPhone);
        if (!CheckUtil.checkPhone(phone)){
            return;
        }

        timer.start();
        //获取验证码
        mLoginModel.getCode(phone);


    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            mBtnYzm.setEnabled(false);
            mBtnYzm.setText((millisUntilFinished / 1000) + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            mBtnYzm.setEnabled(true);
            mBtnYzm.setText("重新获取");
        }
    };

    @Override
    public void Success(RegisterBean registerBean) {
           showToast(registerBean.getMessage()+"");
           Intent intent = new Intent(RegisterActivity.this,SettingPwdActivity.class);
           startActivity(intent);
    }

    @Override
    public void failure(String msg) {
         showToast(msg+"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
