package com.study.glidemodel.util;

import android.app.Activity;

import java.lang.ref.WeakReference;


/**
 * Date:2020/4/10
 * author:lwb
 * Desc:
 */
public class ActivityDestroy {
    /**
     * 判断Activity是否Destroy
     *
     * @return
     */
    public static boolean isDestroy(Activity mActivity) {
        WeakReference mWeak = new WeakReference<Activity>(mActivity);
        mActivity = (Activity) mWeak.get();
        if (mActivity == null || mActivity.isFinishing() || mActivity.isDestroyed()) {
            return true;
        } else {
            return false;
        }
    }
}
