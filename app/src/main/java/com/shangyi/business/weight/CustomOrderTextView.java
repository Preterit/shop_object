package com.shangyi.business.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.shangyi.business.R;
import com.shangyi.kt.ui.order.bean.CommitOrderYhqData;

/**
 * Date:2020/5/9
 * author:lwb
 * Desc:
 */
public class CustomOrderTextView extends LinearLayout {
    private Context mContext;
    private TextView leftText;
    private TextView rightText;

    public CustomOrderTextView(Context context) {
        this(context, null);
    }

    public CustomOrderTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CustomOrderTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.cum_order_yhq_layout, this);
        leftText = findViewById(R.id.leftText);
        rightText = findViewById(R.id.rightText);
    }

    public void setData(String leftTx, String rightTx) {
        leftText.setText(leftTx);
        rightText.setText(rightTx);
    }

    /**
     * 获取代金券的描述信息
     */
    public String getYouhuiquanStr(CommitOrderYhqData bean) {
        String result = "";
        switch (bean.getType()) {
            case 1:
                result = "领券满 " + bean.getFull_price() + " 减 " + bean.getPrice();
                break;
            case 2:
                result = "领券立减 " + bean.getPrice();
                break;
            case 3:
                result = "兑换券";
                break;
        }
        return result;
    }
}
