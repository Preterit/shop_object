package com.shangyi.business.user.register;

import android.text.TextUtils;
import android.util.Log;

import com.shangyi.business.base.BaseMVPPresenter;
import com.shangyi.business.bean.BaseResponse;
import com.shangyi.business.bean.RegisterBean;

import java.util.HashMap;

import io.reactivex.observers.DisposableObserver;

/**
 * data: 2020/3/26 13:52
 * auther: Dz
 * function:
 */
public class RegistPresenter extends BaseMVPPresenter<RegistInterface> {

    public RegisterModel mRegisterModel;
    private String TAG = "RegistPresenter";


    public String isPass(String phone, String pwd) {
        final String PHONE = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        final String PWD = "^[a-zA-Z0-9]{3,16}$";
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
            return "所有输入框不能为空";
        } else {
            if (!phone.matches(PHONE)) {
                return "请输入正确格式的手机号";
            }
            return "yes";
        }
    }


}
