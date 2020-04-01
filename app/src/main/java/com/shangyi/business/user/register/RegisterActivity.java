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
import com.shangyi.business.base.BaseMVPActivity;
import com.shangyi.business.bean.ParmsBean;
import com.shangyi.business.bean.RegisterBean;
import com.shangyi.business.http.RequestCallBack;
import com.shangyi.business.user.login.LoginActivity;
import com.shangyi.business.net.APIServer;
import com.shangyi.business.user.settingpwd.SettingPwdActivity;
import com.shangyi.business.utils.CheckUtil;
import com.shangyi.business.utils.RequestUtils;
import com.shangyi.business.utils.Utils;

import java.util.HashMap;

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

    @Override
    protected void initView() {

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
                break;
            case R.id.btn_gologin:
                Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_yzm:
                //获取验证码
                timer.start();
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

        String pass = presenter.isPass(registerphone, registerCode);
        if (pass.equals("yes")){
            //创建集合，存放参数
            HashMap<String,String> map = new HashMap<>();
            //添加参数
            map.put("phone",registerphone);
            map.put("code",registerCode);

            presenter.getRegister(map);
            Log.e("lz",registerphone + ""+registerCode+"");
        }else {
            showToast(pass);
            Log.e("lz","您输入的手机号格式错误");
        }
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

        new RequestUtils(this, null)
                .tag(TAG)
                .url(APIServer.NETWORK_SMS_CODE)
                .parms(new ParmsBean("mobile", phone))
                .parms(new ParmsBean("type", "regis"))
                .setCallBack(false, new RequestCallBack() {
                    @Override
                    public void onSuccess(JSONObject obj) {
                        LogUtil.e(obj.toString());
                        phoneStr = phone;
                        code = (String) JSON.parseObject(obj.toString()).get("code");
                        timer.start();
                    }
                    @Override
                    public void onError(VolleyError arg0) {
                        super.onError(arg0);
                    }
                });
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
