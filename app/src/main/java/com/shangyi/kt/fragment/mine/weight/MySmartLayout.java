package com.shangyi.kt.fragment.mine.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.donkingliang.consecutivescroller.IConsecutiveScroller;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Date:2020/4/28
 * author:lwb
 * Desc:
 */
public class MySmartLayout extends SmartRefreshLayout implements IConsecutiveScroller {
    public MySmartLayout(Context context) {
        super(context);
    }

    public MySmartLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 返回当前需要滑动的view。
     * 注意：这个view不一定是ViewPager的直接子view，使用者应该根据自己的业务情况返回需要滑动的下级view。
     *
     * @return
     */
    @Override
    public View getCurrentScrollerView() {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view.getX() == getScrollX()) {
                return view;
            }
        }
        return this;
    }

    /**
     * 返回全部需要滑动的下级view
     *
     * @return
     */
    @Override
    public List<View> getScrolledViews() {
        List<View> views = new ArrayList<>();
        int count = getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                views.add(getChildAt(i));
            }
        } else {
            views.add(this);
        }
        return views;
    }
}
