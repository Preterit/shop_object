package com.shangyi.kt.ui.mine;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityYiJianBinding;
import com.shangyi.kt.fragment.mine.model.YijianModel;

import org.jetbrains.annotations.NotNull;

/**
 * 意见反馈
 */
public class YiJianActivity extends BaseKTActivity<ActivityYiJianBinding, YijianModel> {


    private TextView mTvSubmit;

    @NotNull
    @Override
    public Class<YijianModel> vmClazz() {
        return YijianModel.class;
    }

    @Override
    public void bindVM() {

         //dsjdkjs

    }

    @Override
    public void initObserve() {

    }

    @Override
    public int layoutId() {
        return R.layout.activity_yi_jian;
    }

    @Override
    public void initView() {
        mTvSubmit = findViewById(R.id.tv_submit);

        mTvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(@NotNull View v) {
        switch (v.getId()){
            case R.id.tv_submit:
                Intent intent = new Intent(YiJianActivity.this, YiJianSubmitActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                //nothing
                break;
        }
    }
}
