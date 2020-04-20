package com.shangyi.kt.ui.pay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.MyApplication;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityAliPayBinding;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;

public class AliPayActivity extends BaseKTActivity<ActivityAliPayBinding,AliPayModel> implements View.OnClickListener {

    private MyHandler mHandler = new MyHandler(this);
    private String from = "app_id=2021001155645530&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.02%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22314VYGIAGG7ZOYY%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2016-08-15%2012%3A12%3A15&version=1.0&sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D";
    private Button mBtnAliPay;


    @NotNull
    @Override
    public Class<AliPayModel> vmClazz() {
        return AliPayModel.class;
    }

    @Override
    public void bindVM() {

    }

    @Override
    public void initObserve() {

    }

    @Override
    public int layoutId() {
        return R.layout.activity_ali_pay;
    }

    @Override
    public void initView() {
        mBtnAliPay = findViewById(R.id.btn_alipay);

        mBtnAliPay.setOnClickListener(this);
    }


    @Override
    public void onClick(@NotNull View v) {
        switch (v.getId()){
            case R.id.btn_alipay:
                startZfb();
                break;
            default:
                //nothing
                break;
        }
    }

    //调用支付宝支付接口
    private void startZfb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用支付宝
                PayTask payTask = new PayTask(AliPayActivity.this);
                String result = payTask.pay(from, false);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        }).start();
    }


    private static class MyHandler extends Handler {
        private final WeakReference<AliPayActivity> mActivity;

        private MyHandler(AliPayActivity mActivity) {
            this.mActivity = new WeakReference<>(mActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AliPayActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        String result = ((String) msg.obj).replace("{", "")
                                .replace("}", "").replace("resultStatus=", "")
                                .replace("memo=", "").replace("result=", "");
                        Log.d("MainActivity:", result);
                        String num = result.split(";")[0];
                        showPayDialog(num);
                        break;
                }
            }
        }
    }

    //展示吐司
    private static void showPayDialog(String num) {
        String result;
        switch (num) {
            case "9000":
                result = "订单支付成功";
                break;
            case "8000":
                result = "支付结果未知，请联系客服";
                break;
            case "4000":
                result = "订单支付失败";
                break;
            case "5000":
                result = "重复请求";
                break;
            case "6001":
                result = "订单取消成功";
                break;
            case "6002":
                result = "网络连接出错";
                break;

            case "6004":
                result = "支付结果未知，请联系客服";
                break;
            default:
                result = "支付失败，请联系客服";
        }

        Toast.makeText(MyApplication.getContext(), result, Toast.LENGTH_LONG).show();
    }

}
