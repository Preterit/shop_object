package com.shangyi.kt.ui.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityHuiYuanBinding;
import com.shangyi.kt.fragment.mine.bean.MineBean;
import com.shangyi.kt.ui.MainActivity;

import org.jetbrains.annotations.NotNull;

/**
 * 会员信息
 */
public class HuiYuanActivity extends BaseKTActivity<ActivityHuiYuanBinding,HuiYuanModel> {

    private TextView mUserName;
    private TextView mHuiyuanID;

    @NotNull
    @Override
    public Class<HuiYuanModel> vmClazz() {
        return HuiYuanModel.class;
    }

    @Override
    public void bindVM() {
        getMBinding().setVm(getMViewModel());
    }

    @Override
    public void initObserve() {
        getMBinding().getVm().getMineInfo().observe(this, new Observer<MineBean>() {
            @Override
            public void onChanged(MineBean mineBean) {
                if (mineBean == null) {
                } else {
                }
            }
        });
    }

    @Override
    public int layoutId() {
        return R.layout.activity_hui_yuan;
    }

    @Override
    public void initView() {

        mUserName = findViewById(R.id.user_name);
        mHuiyuanID = findViewById(R.id.huiyuan_id);
    }
}
