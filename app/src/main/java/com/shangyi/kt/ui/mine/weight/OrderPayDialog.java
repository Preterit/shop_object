package com.shangyi.kt.ui.mine.weight;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;

import com.alipay.sdk.app.PayTask;
import com.sdxxtop.base.utils.UIUtils;
import com.shangyi.business.R;
import com.shangyi.business.weight.dialog.CancelOrderDialog;
import com.shangyi.kt.ui.mine.bean.PayDialogData;
import com.shangyi.kt.ui.order.bean.WxRequest;
import com.shangyi.kt.ui.order.model.CommitOrderModel;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.lang.ref.WeakReference;

/**
 * Date:2020/5/13
 * author:lwb
 * Desc:
 */
public class OrderPayDialog extends DialogFragment {

    private int payType = 1;   // 支付类型  1 ： 代表支付宝  2 ： 微信
    private ImageView iv_cb_alipay;
    private ImageView iv_cb_wechat;
    private CommitOrderModel orderModel;
    private MyHandler mHandler;
    private MyRunnable myRunnable;  // 支付的任务
    private PayDialogData order;
    private IWXAPI api;

    static class MyHandler extends Handler {
        private OrderPayDialog dialog;

        public MyHandler(OrderPayDialog dialog) {
            WeakReference weakReference = new WeakReference<OrderPayDialog>(dialog);
            this.dialog = (OrderPayDialog) weakReference.get();
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (dialog.getActivity() != null) {
                switch (dialog.payType) {
                    case 1:
                        String str = (String) msg.obj;
                        str = str.replace("{", "")
                                .replace("}", "").replace("resultStatus=", "")
                                .replace("memo=", "").replace("result=", "");
                        String result = str.split(";")[0];
                        showAliPayInfo(result);
                        break;
                }
            }
        }

        /**
         * 支付宝支付订单查询
         */
        private void showAliPayInfo(String num) {
            String result = "";
            switch (num) {
                case "9000":
                    dialog.orderModel.notifyOrder(dialog.order.getOrderId() + "");
                    result = "";
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
                    break;
            }
            if (!TextUtils.isEmpty(result)) {
                UIUtils.showToast(result);
            }
        }
    }

    class MyRunnable implements Runnable {
        private String from;

        public MyRunnable(String from) {
            this.from = from;
        }

        @Override
        public void run() {
            PayTask payTask = new PayTask(getActivity());
            String result = payTask.pay(from, true);
            Message msg = new Message();
            msg.what = 0;
            msg.obj = result;
            mHandler.sendMessage(msg);
        }
    }

    public OrderPayDialog() {

    }

    public static OrderPayDialog newInstance(PayDialogData order) {
        OrderPayDialog dialog = new OrderPayDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable("order", order);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_pay_type_new, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        order = getArguments().getParcelable("order");
        initView(view);
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        TextView tvNum = view.findViewById(R.id.tv_num);
        tvNum.setText(order.getPrice() + "");
        iv_cb_alipay = view.findViewById(R.id.iv_buy_alipay_select);
        iv_cb_wechat = view.findViewById(R.id.iv_buy_weichat_select);
        iv_cb_alipay.setEnabled(true);
        iv_cb_wechat.setEnabled(false);
        view.findViewById(R.id.ll_pay_ali).setOnClickListener(v -> {
            //ali
            payType = 1;
            iv_cb_alipay.setEnabled(true);
            iv_cb_wechat.setEnabled(false);
        });
        view.findViewById(R.id.ll_pay_weichat).setOnClickListener(v -> {
            //wrchat
            payType = 2;
            iv_cb_alipay.setEnabled(false);
            iv_cb_wechat.setEnabled(true);
        });
        view.findViewById(R.id.tv_confirm).setOnClickListener(v -> {
            // 获取订单信息
            getOrderPayInfo();
        });
        view.findViewById(R.id.img_cancel).setOnClickListener(v -> dismiss());
    }

    /**
     * 获取订单信息
     */
    private void getOrderPayInfo() {
        if (payType == 1) {
            orderModel.getPayInfo("" + order.getOrderId(), 1);
        } else {
            if (api.isWXAppInstalled()) {
                orderModel.getWxPayInfo("" + order.getOrderId(), 2);
            } else {
                Toast.makeText(getContext(), "检测到未安装微信支付取消。。。", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 初始化model回掉
     */
    private void initOrderModelEvent() {
        orderModel.getOrderInfo().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                // ali pay info
                if (!TextUtils.isEmpty(s)) {
                    startZfb(s);
                }
            }
        });
        orderModel.getWxPayInfo().observe(this, new Observer<WxRequest>() {
            @Override
            public void onChanged(WxRequest wxRequest) {
                // wchat pay info
                if (wxRequest != null) {
                    startWx(wxRequest);
                }
            }
        });
        orderModel.getAliPaySuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    UIUtils.showToast("支付成功");
                    dismiss();
                }
            }
        });
    }

    private void startWx(WxRequest wxRequest) {
        /**
         * 微信支付
         */
        PayReq req = new PayReq();
        req.appId = wxRequest.getAppid();
        req.partnerId = wxRequest.getPartnerid();
        req.prepayId = wxRequest.getPrepayid();
        req.nonceStr = wxRequest.getNoncestr();
        req.timeStamp = wxRequest.getTimestamp() + "";
        req.packageValue = wxRequest.getPackage();
        req.sign = wxRequest.getSign();
        req.extData = "app data"; // optional
        api.sendReq(req);
    }

    /**
     * 支付宝支付
     */
    private void startZfb(String from) {
        myRunnable = new MyRunnable(from);
        new Thread(myRunnable).start();
    }

    @Override
    public void onResume() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.height = (height / 5) * 3;
        Window window = getDialog().getWindow();
        window.setAttributes((WindowManager.LayoutParams) params);
        window.setGravity(Gravity.BOTTOM);

        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialogStyleBottom); //dialog全屏
        orderModel = new CommitOrderModel();
        mHandler = new MyHandler(this);
        initOrderModelEvent();
        api = WXAPIFactory.createWXAPI(getContext(), "wx8c512b137c836be1");
        api.registerApp("wx8c512b137c836be1");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        orderModel = null;
        mHandler.removeCallbacks(myRunnable);
        mHandler = null;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mListener != null) {
            mListener.onDismiss();
        }
    }

    public interface OnDissListener {
        void onDismiss();
    }

    private CancelOrderDialog.OnDissListener mListener;

    public void setOnDismiss(CancelOrderDialog.OnDissListener listener) {
        this.mListener = listener;
    }


}
