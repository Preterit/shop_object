package com.shangyi.kt.ui.mine.weight;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.wuhenzhizao.sku.R;
import com.wuhenzhizao.sku.utils.ScreenUtils;

/**
 * Created by wuhenzhizao on 2017/7/31.
 */

public class HomeYhqItemView extends androidx.appcompat.widget.AppCompatTextView {
    private String attributeValue;

    public HomeYhqItemView(Context context) {
        super(context);
        init(context);
    }

    public HomeYhqItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HomeYhqItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setBackgroundResource(R.drawable.shape_home_yhq_item_bg);
        setTextColor(Color.parseColor("#FF2943"));
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
        setGravity(Gravity.CENTER);
        setPadding(ScreenUtils.dp2PxInt(context, 10), ScreenUtils.dp2PxInt(context, 2), ScreenUtils.dp2PxInt(context, 10), ScreenUtils.dp2PxInt(context, 2));

        setMinWidth(ScreenUtils.dp2PxInt(context, 45));
        setMaxWidth(ScreenUtils.dp2PxInt(context, 200));

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) getLayoutParams();
        lp.setMargins(0, 0, ScreenUtils.dp2PxInt(context, 5), 0);
        setLayoutParams(lp);
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
        setText(attributeValue);
    }
}
