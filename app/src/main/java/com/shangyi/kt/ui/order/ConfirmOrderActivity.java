package com.shangyi.kt.ui.order;


import android.view.LayoutInflater;
import android.view.View;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityConfirmOrderBinding;

import org.jetbrains.annotations.NotNull;


/**
 * 确认订单页面
 */
public class ConfirmOrderActivity extends BaseKTActivity<ActivityConfirmOrderBinding,ConfirmOrderModel> {


    private View mConfirmOrderAddressItem;

    @NotNull
    @Override
    public Class<ConfirmOrderModel> vmClazz() {
        return ConfirmOrderModel.class;
    }

    @Override
    public void bindVM() {

    }

    @Override
    public void initObserve() {

    }

    @Override
    public int layoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initView() {

        //确认订单 选择地址item
        mConfirmOrderAddressItem = getLayoutInflater().inflate(R.layout.confirm_order_goods_item, null);



    }
}
