package com.shangyi.kt.ui.order;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityOrderSuccessBinding;
import com.shangyi.kt.ui.order.adapter.SuccessOrderTuijianAdapter;
import com.shangyi.kt.ui.order.model.OrderSuccessModel;
import com.shangyi.kt.ui.order.bean.OrderBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 订单支付成功页面
 */
public class OrderSuccessActivity extends BaseKTActivity<ActivityOrderSuccessBinding, OrderSuccessModel> {


    private RecyclerView mSuccessOrderTuijianRcy;
    private SuccessOrderTuijianAdapter mSuccessOrderTuijianAdapter;
    private TextView mTvPrice;
    private LinearLayout mLlShopping;
    private String mPrice;

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
        mPrice = getIntent().getStringExtra("price");
        mSuccessOrderTuijianRcy = findViewById(R.id.success_order_tuijian_rcy);

        mLlShopping = findViewById(R.id.ll_shopping);

        mTvPrice.setText("￥"+ mPrice);

        mSuccessOrderTuijianAdapter = new SuccessOrderTuijianAdapter();
        mSuccessOrderTuijianRcy.setLayoutManager(new GridLayoutManager(OrderSuccessActivity.this,2));
        mSuccessOrderTuijianRcy.setAdapter(mSuccessOrderTuijianAdapter);

        mLlShopping.setOnClickListener(this);

    }

    @Override
    public void initData() {
        getMBinding().getVm().successOrderTuijian(000);
    }

    @Override
    public void onClick(@NotNull View v) {
        switch (v.getId()){
            case R.id.ll_shopping://继续购物
                finish();
                break;
            default:
                //nothing
                break;
        }
    }
}
