package com.shangyi.kt.ui.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityOrderSuccessBinding;
import com.shangyi.kt.ui.pingjia.OrderBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 订单支付成功页面
 */
public class OrderSuccessActivity extends BaseKTActivity<ActivityOrderSuccessBinding,OrderSuccessModel> {


    private RecyclerView mSuccessOrderTuijianRcy;
    private SuccessOrderTuijianAdapter mSuccessOrderTuijianAdapter;
    private TextView mTvPrice;

    @NotNull
    @Override
    public Class<OrderSuccessModel> vmClazz() {
        return OrderSuccessModel.class;
    }

    @Override
    public void bindVM() {
        getMBinding().setVm(getMViewModel());
    }

    @Override
    public void initObserve() {
        getMBinding().getVm().getSuccessData().observe(this, new Observer<List<OrderBean>>() {
            @Override
            public void onChanged(List<OrderBean> orderBeans) {
                mSuccessOrderTuijianAdapter.setList(orderBeans);
            }
        });
    }

    @Override
    public int layoutId() {
        return R.layout.activity_order_success;
    }

    private float price;

    @Override
    public void initView() {
        mTvPrice = findViewById(R.id.tv_price);
        price= getIntent().getFloatExtra("price",0f);
        mSuccessOrderTuijianRcy = findViewById(R.id.success_order_tuijian_rcy);

        mTvPrice.setText("￥"+price+"");
        mSuccessOrderTuijianAdapter = new SuccessOrderTuijianAdapter();
        mSuccessOrderTuijianRcy.setLayoutManager(new GridLayoutManager(OrderSuccessActivity.this,2));
        mSuccessOrderTuijianRcy.setAdapter(mSuccessOrderTuijianAdapter);

    }

    @Override
    public void initData() {
        getMBinding().getVm().successOrderTuijian(000);
    }
}
