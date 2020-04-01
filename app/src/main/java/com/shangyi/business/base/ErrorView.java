package com.shangyi.business.base;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shangyi.business.R;

/**
 * data: 2020/3/18 9:30
 * auther: Dz
 * function:
 */
public class ErrorView extends LinearLayout {

    private View mInflate;
    private RelativeLayout mError;

    public ErrorView(Context context) {
        super(context,null);
    }

    public ErrorView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflate = View.inflate(context,R.layout.net,this);
        initView(context);
    }

    private void initView(final Context context) {
        mError = mInflate.findViewById(R.id.error);
        mError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                context.startActivity(intent);
            }
        });

    }
}
