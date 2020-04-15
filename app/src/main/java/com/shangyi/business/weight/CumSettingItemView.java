package com.shangyi.business.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shangyi.business.R;

import androidx.annotation.Nullable;

/**
 * data: 2020/4/15 11:42
 * auther: Dz
 * function:自定义条目
 */
public class CumSettingItemView extends LinearLayout {

    private ImageView mItemOne;
    private TextView mItemTwo;
    private TextView mItemThree;
    private ImageView mItemFour;
    private String mLeftText;
    private Drawable mRightImg;
    private String mRightText;
    private Drawable mLeftImg;
    private boolean mRightTextIsShow;
    private boolean mRightImgIsShow;

    public CumSettingItemView(Context context) {
        this(context,null);
    }

    public CumSettingItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CumSettingItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.cum_setting_view,this,true);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CumSettingItemView, defStyleAttr, 0);
        mLeftImg = a.getDrawable(R.styleable.CumSettingItemView_leftImg);
        mLeftText = a.getString(R.styleable.CumSettingItemView_leftText);
        mRightText = a.getString(R.styleable.CumSettingItemView_rightText);
        mRightImg = a.getDrawable(R.styleable.CumSettingItemView_rightImg);

        mRightTextIsShow = a.getBoolean(R.styleable.CumSettingItemView_rightTextIsShow, false);
        mRightImgIsShow = a.getBoolean(R.styleable.CumSettingItemView_rightImgIsShow, false);

        a.recycle();
        initView();
    }

    private void initView() {
        mItemOne = findViewById(R.id.item_one);
        mItemTwo = findViewById(R.id.item_two);
        mItemThree = findViewById(R.id.item_three);
        mItemFour = findViewById(R.id.item_four);

        mItemOne.setImageDrawable(mLeftImg);
        mItemTwo.setText(mLeftText);
        mItemThree.setText(mRightText);
        mItemFour.setImageDrawable(mRightImg);

        mItemThree.setVisibility(mRightTextIsShow?View.VISIBLE:View.INVISIBLE);
        mItemFour.setVisibility(mRightImgIsShow?View.VISIBLE:View.INVISIBLE);

    }
}
