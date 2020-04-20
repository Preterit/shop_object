package com.shangyi.business.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shangyi.business.R;

import androidx.annotation.Nullable;

/**
 * data: 2020/4/17 14:06
 * auther: Dz
 * function:自定义item
 */
public class CumAboutItemView extends LinearLayout {

    private String mLeftText;
    private Drawable mRightImg;
    private TextView mAboutText;
    private ImageView mAboutImg;

    public CumAboutItemView(Context context) {
        this(context,null);
    }

    public CumAboutItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CumAboutItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.cum_about_view,this,true);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CumAboutItemView, defStyleAttr, 0);

        mLeftText = a.getString(R.styleable.CumAboutItemView_aboutleftText);
        mRightImg = a.getDrawable(R.styleable.CumAboutItemView_aboutrightImg);

        a.recycle();
        initView();
    }

    private void initView() {
        mAboutText = findViewById(R.id.about_text);
        mAboutImg = findViewById(R.id.about_img);

        mAboutText.setText(mLeftText);
        mAboutImg.setImageDrawable(mRightImg);

    }
}
