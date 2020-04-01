package com.shangyi.business.register;

import com.shangyi.business.bean.RegisterBean;

/**
 * data: 2020/3/26 13:51
 * auther: Dz
 * function:
 */
public interface RegistInterface {
    void Success(RegisterBean registerBean);

    void failure(String msg);
}
