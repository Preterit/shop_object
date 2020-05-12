package com.shangyi.business.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import sing.util.MatchUtil;
import sing.util.ToastUtil;


public class CheckUtil {

    // 检验手机号
    public static boolean checkPhone(String str) {
        if (TextUtils.isEmpty(str)) {
            ToastUtil.showShort("请输入手机号");
            return false;
        }

        if (!MatchUtil.isPhone(str)) {
            ToastUtil.showShort("请输入正确手机号");
            return false;
        }
        return true;
    }

    // 检验昵称
    public static boolean checkName(String str) {
        if (TextUtils.isEmpty(str)) {
            ToastUtil.showShort("请输入昵称");
            return false;
        }
        return true;
    }

    // 检验昵称
    public static boolean checkCode(String inputCode, String code) {
        if (TextUtils.isEmpty(code)) {
            ToastUtil.showShort("请获取验证码");
            return false;
        }

        if (TextUtils.isEmpty(inputCode)) {
            ToastUtil.showShort("请输入验证码");
            return false;
        }

        if (!inputCode.equals(code)) {
            ToastUtil.showShort("验证码不正确");
            return false;
        }

        return true;
    }

    // 检验密码
    public static boolean checkPassword(String passerd, String passerdTwo) {
        if (TextUtils.isEmpty(passerd)) {
            ToastUtil.showShort("请输入密码");
            return false;
        }
        if (TextUtils.isEmpty(passerd)) {
            ToastUtil.showShort("请再次输入密码");
            return false;
        }

        if (!passerd.equals(passerdTwo)) {
            ToastUtil.showShort("两次密码不一致");
            return false;
        }
        return true;
    }

    /**
     * 检测是否安装支付宝
     *
     * @param context
     * @return
     */
    public static boolean isAliPayInstalled(Context context) {
        WeakReference<Context> cx = new WeakReference<>(context);
        Context mContext = cx.get();
        if (mContext == null) return false;
        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(mContext.getPackageManager());
        return componentName != null;
    }

    /**
     * 检测是否安装微信
     *
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        WeakReference<Context> cx = new WeakReference<>(context);
        Context mContext = cx.get();
        if (mContext == null) return false;
        final PackageManager packageManager = mContext.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }
}
