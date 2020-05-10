package com.shangyi.kt.ui.mine;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityYiJianSubmitBinding;
import com.shangyi.kt.fragment.mine.model.YijianSubmitModel;

import org.jetbrains.annotations.NotNull;

public class YiJianSubmitActivity extends BaseKTActivity<ActivityYiJianSubmitBinding, YijianSubmitModel> {

    @NotNull
    @Override
    public Class<YijianSubmitModel> vmClazz() {
        return YijianSubmitModel.class;
    }

    @Override
    public void bindVM() {

        //dsjkjhd
    }

    @Override
    public void initObserve() {

    }

    @Override
    public int layoutId() {


        return R.layout.activity_yi_jian_submit;
    }

    @Override
    public void initView() {

    }
}
