package com.shangyi.kt.ui.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityOrderSuccessBinding;

import org.jetbrains.annotations.NotNull;

/**
 * 订单支付成功页面
 */
public class OrderSuccessActivity extends BaseKTActivity<ActivityOrderSuccessBinding,OrderSuccessModel> {

    @NotNull
    @Override
    public Class<OrderSuccessModel> vmClazz() {
        return OrderSuccessModel.class;
    }

    @Override
    public void bindVM() {

    }

    @Override
    public void initObserve() {

    }

    @Override
    public int layoutId() {
        return R.layout.activity_order_success;
    }

    @Override
    public void initView() {

    }
}
