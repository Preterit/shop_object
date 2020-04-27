package com.shangyi.business.wxapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.shangyi.business.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.shangyi.business.api.Constom.WXAPP_ID;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private static final String TAG = "WXPayEntryActivity";
    private IWXAPI iwxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);

        iwxapi = WXAPIFactory.createWXAPI(this, null);
        iwxapi.registerApp(WXAPP_ID);//appID
        iwxapi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.e(TAG, "onResp: " + baseReq.toString());
    }

    /**
     * 处理支付回调
     *
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Log.e(TAG, "onResp: " + baseResp.toString());
            if (baseResp.errCode == 0) {
                //ToastUtils.showToast("支付成功");
            } else if (baseResp.errCode == -1) {
                //  ToastUtils.showToast("支付失败，请联系客服！");
            } else {
                //  ToastUtils.showToast("支付取消！");
            }
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        iwxapi.handleIntent(intent, this);
    }
}
