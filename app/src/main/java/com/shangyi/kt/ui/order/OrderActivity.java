package com.shangyi.kt.ui.order;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.sdxxtop.base.BaseKTActivity;
import com.shangyi.business.MyApplication;
import com.shangyi.business.R;
import com.shangyi.business.databinding.ActivityConfirmOrderBinding;
import com.shangyi.kt.ui.order.adapter.ConfirmOrderGoodsAdapter;
import com.shangyi.kt.ui.order.model.OrderSuccessModel;
import com.shangyi.kt.ui.pingjia.OrderDataBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 确认订单页面
 */
public class OrderActivity extends BaseKTActivity<ActivityConfirmOrderBinding, OrderSuccessModel>{

    private RecyclerView mConfirmOrderGoodsRcy;
    private ConfirmOrderGoodsAdapter mConfirmOrderGoodsAdapter;
    private TextView mTvCommitOrder;


    private ImageView mIvWeichatSelect;
    private ImageView mIvAliSelect;

    private static final int PAY_TYPE_WXPAY = 0;  //微信支付,默认支付方式
    private static final int PAY_TYPE_ALIPAY = 1;  //支付宝支付
    private int payType = -1;
    private MyHandler mHandler = new MyHandler(this);
    private IWXAPI iwxapi;
    private String from = "app_id=2021001155645530&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.02%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22314VYGIAGG7ZOYY%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2016-08-15%2012%3A12%3A15&version=1.0&sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D";
    private TextView mPayDjs;
    private long countdownTime;//倒计时的总时间(单位:毫秒)
    private String timefromServer;//从服务器获取的订单生成时间
    private long chaoshitime;//从服务器获取订单有效时长(单位:毫秒)
    private boolean isStop;
    private View mDialogView;
    private PayBottomDialog mDialog;
    private TextView mTvNum;

    @NotNull
    @Override
    public Class<OrderSuccessModel> vmClazz() {
        return OrderSuccessModel.class;
    }

    @Override
    public void bindVM() {
        getMBinding().setVm(getMViewModel());
    }

    @Override
    public void initObserve() {
        getMBinding().getVm().getOrderData().observe(this, new Observer<List<OrderDataBean>>() {
            @Override
            public void onChanged(List<OrderDataBean> orderDataBeans) {
                mConfirmOrderGoodsAdapter.setData(orderDataBeans);
            }
        });
    }

    @Override
    public void initData() {
        getMBinding().getVm().querenOrder(1,1);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initView() {
        price = getIntent().getFloatExtra("price", 0f);


        mConfirmOrderGoodsRcy = findViewById(R.id.confirm_order_goods_rcy);
        mTvCommitOrder = findViewById(R.id.tv_commit_order);
        mConfirmOrderGoodsAdapter = new ConfirmOrderGoodsAdapter(this);
        mConfirmOrderGoodsRcy.setAdapter(mConfirmOrderGoodsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mConfirmOrderGoodsRcy.setLayoutManager(linearLayoutManager);

        mTvCommitOrder.setOnClickListener(this);


        mDialogView = getLayoutInflater().inflate(R.layout.dialog_pay_type, null);
        mDialog = new PayBottomDialog(OrderActivity.this, mDialogView, new int[]{R.id.ll_pay_weichat, R.id.ll_pay_ali, R.id.tv_confirm, R.id.img_cancel});
        //倒计时
        mPayDjs = mDialogView.findViewById(R.id.pay_djs);

        mTvNum = mDialogView.findViewById(R.id.tv_num);
        getTimeDuring();
        //微信支付的选择
        mIvWeichatSelect = mDialogView.findViewById(R.id.iv_buy_weichat_select);
        //支付宝的选择
        mIvAliSelect = mDialogView.findViewById(R.id.iv_buy_alipay_select);
    }


    private static class MyHandler extends Handler {
        private final WeakReference<OrderActivity> mActivity;

        private MyHandler(OrderActivity mActivity) {
            this.mActivity = new WeakReference<>(mActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            OrderActivity activity = mActivity.get();
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

    private float price = 3000.98f;

    private void pay() {
        mTvNum.setText(price+"");
        mDialog.bottmShow();
        mDialog.setOnBottomItemClickListener(new PayBottomDialog.OnBottomItemClickListener() {
            @Override
            public void onBottomItemClick(PayBottomDialog dialog, View view) {
                switch (view.getId()) {
                    case R.id.ll_pay_ali:  //支付宝支付
                        showToast("支付宝支付");
                        if (PAY_TYPE_ALIPAY != payType) {
                            mIvWeichatSelect.setImageDrawable(getResources().getDrawable(R.drawable.weixuan_item));
                            mIvAliSelect.setImageDrawable(getResources().getDrawable(R.drawable.xuanzhong_item));
                            payType = PAY_TYPE_ALIPAY;
                        }
                        break;
                    case R.id.ll_pay_weichat:   //微信支付
                        showToast("微信支付");
                        if (PAY_TYPE_WXPAY != payType) {
                            mIvWeichatSelect.setImageDrawable(getResources().getDrawable(R.drawable.xuanzhong_item));
                            mIvAliSelect.setImageDrawable(getResources().getDrawable(R.drawable.weixuan_item));
                            payType = PAY_TYPE_WXPAY;
                        }

                        break;
                    case R.id.tv_confirm:  //确认支付
                        //TODO 支付
                        showToast("确认支付");

                        Intent intent = new Intent(OrderActivity.this,OrderSuccessActivity.class);
                        intent.putExtra("price",price);
                        startActivity(intent);
                        finish();
                       /* if (payType == PAY_TYPE_ALIPAY){
                            startZfb();
                        }else if (payType == PAY_TYPE_WXPAY){
                            startWx();
                        }*/
                        dialog.cancel();
                        break;
                    case R.id.img_cancel:  //取消支付
                        showToast("取消支付");
                        //是否确定取消支付
                        IsPay();
                        dialog.cancel();
                        break;
                }
            }
        });
    }

    /**
     * 是否取消支付弹框
     */
    private void IsPay() {
        View isDialogView = getLayoutInflater().inflate(R.layout.dialog_ispay_type, null);
        PayBottomDialog isDialog = new PayBottomDialog(OrderActivity.this, isDialogView , new int[]{R.id.tv_queren,R.id.tv_jx_pay});
        isDialog.bottmShow();
        isDialog.setOnBottomItemClickListener(new PayBottomDialog.OnBottomItemClickListener() {
            @Override
            public void onBottomItemClick(PayBottomDialog payBottomDialog, View view) {
                switch (view.getId()){
                    case R.id.tv_queren://确定离开
                        finish();
                        break;
                    case R.id.tv_jx_pay://继续支付
                        pay();
                        break;
                    default:
                        //nothing
                        break;
                }
            }
        });


    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isStop){
                return;
            }
            countdownTime -= 1000;//倒计时总时间减1

            SimpleDateFormat minforamt = new SimpleDateFormat("mm:ss");

            String hms = minforamt.format(countdownTime);//格式化倒计时的总时间
            mPayDjs.setText("请在" + hms+"分内完成支付");
            handler.postDelayed(this, 1000);
        }
    };


    /**
     * 订单倒计时 实现
     */
    private void getTimeDuring() {
        chaoshitime = 30*60*1000;

        timefromServer = "2020-04-20 16:50:20";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date serverDate = dateFormat.parse(timefromServer);
            long duringTime = new Date().getTime() - serverDate.getTime();//计算当前时间和从服务器获取的订单生成的时间差
            countdownTime = chaoshitime-duringTime;//计算倒计时的总时间
            handler.postDelayed(runnable,1000);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_commit_order://确认下单
                pay();
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
                PayTask payTask = new PayTask(OrderActivity.this);
                String result = payTask.pay(from, false);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        }).start();
    }


    /**
     * 调用微信支付接口
     */
    private void startWx() {
        iwxapi = WXAPIFactory.createWXAPI(this, null);
        iwxapi.registerApp("wx8c512b137c836be1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
                //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = "wx8c512b137c836be1";
                request.partnerId = "12345";
                request.prepayId = "1234";
                request.packageValue = "Sign=WXPay";
                request.nonceStr = "12324";
                request.timeStamp = "1234";
                request.sign = "1234567890987654";
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        }).start();
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
        isStop = true;
        runnable = null;
        handler = null;
    }
}