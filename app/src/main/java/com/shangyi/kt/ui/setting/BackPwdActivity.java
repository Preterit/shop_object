package com.shangyi.kt.ui.setting;


import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityBackPwdBinding;

import org.jetbrains.annotations.NotNull;

/**
 * 账户安全
 * 修改密码
 */
public class BackPwdActivity extends BaseKTActivity<ActivityBackPwdBinding,BackPwdModel> {

    @NotNull
    @Override
    public Class<BackPwdModel> vmClazz() {
        return BackPwdModel.class;
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
        return R.layout.activity_back_pwd;
    }

    @Override
    public void initView() {

    }
}
