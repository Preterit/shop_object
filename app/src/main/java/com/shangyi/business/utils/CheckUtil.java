package com.shangyi.business.utils;

import android.text.TextUtils;

import sing.util.MatchUtil;
import sing.util.ToastUtil;


public class CheckUtil {

    // 检验手机号
    public static boolean checkPhone(String str){
        if (TextUtils.isEmpty(str)){
            ToastUtil.showShort("请输入手机号");
            return false;
        }

        if (!MatchUtil.isPhone(str)){
            ToastUtil.showShort("请输入正确手机号");
            return false;
        }
        return true;
    }

    // 检验昵称
    public static boolean checkName(String str){
        if (TextUtils.isEmpty(str)){
            ToastUtil.showShort("请输入昵称");
            return false;
        }
        return true;
    }
    // 检验昵称
    public static boolean checkCode(String inputCode, String code){
        if (TextUtils.isEmpty(code)){
            ToastUtil.showShort("请获取验证码");
            return false;
        }

        if (TextUtils.isEmpty(inputCode)){
            ToastUtil.showShort("请输入验证码");
            return false;
        }

        if (!inputCode.equals(code)){
            ToastUtil.showShort("验证码不正确");
            return false;
        }

        return true;
    }

    // 检验密码
    public static boolean checkPassword(String passerd, String passerdTwo) {
        if (TextUtils.isEmpty(passerd)){
            ToastUtil.showShort("请输入密码");
            return false;
        }
        if (TextUtils.isEmpty(passerd)){
            ToastUtil.showShort("请再次输入密码");
            return false;
        }

        if (!passerd.equals(passerdTwo)){
            ToastUtil.showShort("两次密码不一致");
            return false;
        }
        return true;
    }
}
