package com.shangyi.business.utils;

import android.os.CountDownTimer;
import android.widget.TextView;


import java.lang.ref.WeakReference;

/**
 * Date:2020/4/3
 * author:lwb
 * Desc:
 */
public class TimerUtil {

    private TextView mBtn;

    public TimerUtil(TextView target) {
        WeakReference weakReference = new WeakReference<TextView>(target);
        mBtn = (TextView) weakReference.get();
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            if (null == mBtn) return;
            mBtn.setEnabled(false);
            mBtn.setText((millisUntilFinished / 1000) + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            if (null == mBtn) return;
            mBtn.setEnabled(true);
            mBtn.setText("重新获取");
        }
    };

    public void start() {
        if (null != timer) {
            timer.start();
        }
    }
}
