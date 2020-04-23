package com.shangyi.business.weight;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.shangyi.business.R;
import com.shangyi.business.network.Constants;
import com.shangyi.business.network.SpUtil;
import com.shangyi.kt.ui.userlogin.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :  lwb
 * Date: 2019/9/4
 * Desc:
 */
public class CustomBottomTab extends LinearLayout implements View.OnClickListener {

    private int mCurrentItem = 0;
    private ImageView imgHome, imgMine, imgCar, imgCategroy;
    private TextView tvMine, tvHome, tvCategroy, tvCar;
    private ConstraintLayout conMine, conCar, conCategroy, conHome;

    public CustomBottomTab(Context context) {
        this(context, null);
    }

    public CustomBottomTab(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomBottomTab(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_bottom_tab, this, true);

        imgHome = findViewById(R.id.imgHome);
        imgCategroy = findViewById(R.id.imgCategroy);
        imgCar = findViewById(R.id.imgCar);
        imgMine = findViewById(R.id.imgMine);

        tvHome = findViewById(R.id.tvHome);
        tvCategroy = findViewById(R.id.tvCategroy);
        tvCar = findViewById(R.id.tvCar);
        tvMine = findViewById(R.id.tvMine);

        conHome = findViewById(R.id.conHome);
        conCategroy = findViewById(R.id.conCategroy);
        conCar = findViewById(R.id.conCar);
        conMine = findViewById(R.id.conMine);


        conHome.setOnClickListener(this);
        conCategroy.setOnClickListener(this);
        conCar.setOnClickListener(this);
        conMine.setOnClickListener(this);

        //初始化默认状态。
        setViewEnable();
    }

    /**
     * tab 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.conHome:
                mCurrentItem = 0;
                setViewEnable();
                break;
            case R.id.conCategroy:
                mCurrentItem = 1;
                setViewEnable();
                break;
            case R.id.conCar:
                mCurrentItem = 2;
                break;
            case R.id.conMine:
                mCurrentItem = 3;
                break;
        }
//        setViewEnable();
        if (null != mListener) {
            mListener.nextResult(mCurrentItem);
        }
    }


    public void setViewEnable() {
        tvHome.setEnabled(false);
        tvCategroy.setEnabled(false);
        tvCar.setEnabled(false);
        tvMine.setEnabled(false);
        imgHome.setEnabled(false);
        imgCategroy.setEnabled(false);
        imgCar.setEnabled(false);
        imgMine.setEnabled(false);
        switch (mCurrentItem) {
            case 0: // 首页
                tvHome.setEnabled(true);
                imgHome.setEnabled(true);
                break;
            case 1:  // 分类
                tvCategroy.setEnabled(true);
                imgCategroy.setEnabled(true);
                break;
            case 2:  // 购物车
                tvCar.setEnabled(true);
                imgCar.setEnabled(true);
                break;
            case 3:  // 我的
                tvMine.setEnabled(true);
                imgMine.setEnabled(true);
                break;
        }
    }

    public interface OnMenuClickListener {
        void nextResult(int menu);
    }

    private OnMenuClickListener mListener;

    public void setOnMenuClickListener(OnMenuClickListener listener) {
        this.mListener = listener;
    }
}
