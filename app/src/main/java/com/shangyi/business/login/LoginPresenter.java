package com.shangyi.business.login;

import android.text.TextUtils;

import com.shangyi.business.base.BaseMVPPresenter;
import com.shangyi.business.register.RegistInterface;

/**
 * data: 2020/3/26 14:53
 * auther: Dz
 * function:
 */
public class LoginPresenter extends BaseMVPPresenter<RegistInterface> {

    /**
     * 手机号、密码
     * 正则验证
     *
     * @param phone
     * @param pwd
     * @return
     */
    public String DataNotNull(String phone, String pwd) {
        final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        final String REGEX_PASSWORD = "^[a-zA-Z0-9]{3,16}$";

        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
            if (phone.matches(REGEX_MOBILE) && pwd.matches(REGEX_PASSWORD)) {
                return "正确";
            } else {
                return "账号密码格式错误";
            }
        } else {
            return "账号密码必填";
        }
    }
}
