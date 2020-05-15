package com.shangyi.kt.ui.home.activity;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityHomePinpaiBinding;
import com.shangyi.kt.ui.home.adapter.PinpaiAdapter;
import com.shangyi.kt.ui.home.bean.PinPaiBean;
import com.shangyi.kt.ui.home.model.PinpaiModel;
import com.shangyi.kt.ui.order.bean.OrderBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomePinpaiActivity extends BaseKTActivity<ActivityHomePinpaiBinding, PinpaiModel> {

    private RecyclerView mPinpaiRey;
    private PinpaiAdapter mPinpaiAdapter;
    @NotNull
    @Override
    public Class<PinpaiModel> vmClazz() {
        return PinpaiModel.class;
    }

    @Override
    public void bindVM() {
        getMBinding().setVm(getMViewModel());
    }

    @Override
    public void initObserve() {
        getMBinding().getVm().getSuccessData().observe(this, new Observer<List<PinPaiBean>>() {
            @Override
            public void onChanged(List<PinPaiBean> pinPaiBeans) {
                mPinpaiAdapter.setList(pinPaiBeans);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        getMBinding().getVm().pinPaiTuijian("005");
    }

    @Override
    public int layoutId() {
        return R.layout.activity_home_pinpai;
    }

    @Override
    public void initView() {

        mPinpaiRey = findViewById(R.id.pinpai_recyclerview);
        mPinpaiAdapter = new PinpaiAdapter();
        mPinpaiRey.setLayoutManager(new LinearLayoutManager(HomePinpaiActivity.this,LinearLayoutManager.HORIZONTAL ,false));
        mPinpaiRey.setAdapter(mPinpaiAdapter);

        /*View headView = LayoutInflater.from(this).inflate(R.layout.pinpai_item_view, null);
        mPinpaiAdapter.addHeaderView(headView);*/

    }
}
