package com.shangyi.business.base;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.shangyi.business.R;

/**
 * 加载框
 */
public class LoadingDialog {

    private Dialog dialog;

    public LoadingDialog(Context context) {
        dialog = new Dialog(context, R.style.loading_dialog);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);

        dialog.setContentView(R.layout.loading_dialog);

        ImageView iv = dialog.findViewById(R.id.iv);
        Glide.with(context).load(R.drawable.loading).into(iv);
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public boolean isShowing() {
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
            dialog.cancel();
        }
    }
}
