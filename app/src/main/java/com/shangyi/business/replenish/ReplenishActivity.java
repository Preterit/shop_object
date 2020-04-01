package com.shangyi.business.replenish;

import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.shangyi.business.R;
import com.shangyi.business.base.BaseMVPActivity;

/**
 *
 * 补充资料页面
 */
public class ReplenishActivity extends BaseMVPActivity<ReplenishInterface,ReplenishPresenter> implements ReplenishInterface, View.OnClickListener {

    private TextView mSetPwd;
    private TextView mRegisterJump;

    @Override
    protected void initView() {
        mSetPwd = findViewById(R.id.setpwd);

        mRegisterJump = findViewById(R.id.register_jump);

        //字体加粗
        mSetPwd.getPaint().setFakeBoldText(true);
        //添加下划线
        mRegisterJump.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int setView() {
        return R.layout.activity_replenish;
    }

    @Override
    protected ReplenishPresenter initPresenter() {
        return new ReplenishPresenter();
    }

    @Override
    public void onClick(View v) {

    }
}
