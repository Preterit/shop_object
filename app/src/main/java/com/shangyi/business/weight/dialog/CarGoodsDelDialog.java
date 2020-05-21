package com.shangyi.business.weight.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.shangyi.business.R;

public class CarGoodsDelDialog extends Dialog {
    public Context context;

    public CarGoodsDelDialog(Context context) {
        super(context, R.style.loading_dialog);
        this.context = context;
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setWindowAnimations(R.style.LoadingDialogWindowStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.car_goods_del_dialog);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);

        findViewById(R.id.tvConfirm).setOnClickListener(mListener);
        findViewById(R.id.tvCancel).setOnClickListener(
                v -> {
                    dismiss();
                }
        );
    }

    private View.OnClickListener mListener;

    public void setOnConfirmClick(View.OnClickListener listener) {
        this.mListener = listener;
    }


}
