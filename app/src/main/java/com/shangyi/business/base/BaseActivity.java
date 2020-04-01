package com.shangyi.business.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.shangyi.business.NetWorkActivity;
import com.shangyi.business.R;

/**
 * data: 2020/3/17 17:36
 * auther: Dz
 * function:
 */
public abstract class BaseActivity extends AppCompatActivity {

    Toast mToast;

    private Toolbar mBacktoolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setView());

        mBacktoolbar = findViewById(R.id.toolbar);
        setBack(null);
        initView();

        if (!isConnNet()) {
            showToast("亲~您的手机没有网络了哟~~");
            Intent intent = new Intent(this, NetWorkActivity.class);
            startActivity(intent);
            finish();
        } else {
            initData();
            setListener();
        }
    }

    public void setBack(View.OnClickListener listener){
        if (mBacktoolbar != null){
            ImageView iv = mBacktoolbar.findViewById(R.id.iv_back);
            if (iv == null){
                return;
            }

            if (listener != null){
                iv.setOnClickListener(listener);
            }else {
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContextHandler.finish();
                    }
                });
            }
        }

    }
    /**
     * 判断网络
     * @return
     */
    public boolean isConnNet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }

    /**
     * 吐司方法
     *
     * @param s
     */
    public void showToast(String s) {
        if (!TextUtils.isEmpty(s)) {
            if (mToast == null) {
                mToast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(s);
            }
            mToast.setText(s);
        }
    }

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract int setView();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 申请权限
     * @param code
     * @param permissions
     */
    public void requestPermission(int code,String... permissions){
        ActivityCompat.requestPermissions(this,permissions,code);
    }
    public void _setHintBack(int visibility) {
        if (mBacktoolbar != null) {
            ImageView iv = mBacktoolbar.findViewById(R.id.iv_back);
            iv.setVisibility(visibility);
        }
    }

    public void _setTvBackStatu(int visibility) {
        if (mBacktoolbar != null) {
            TextView tv = mBacktoolbar.findViewById(R.id.tv_back);
            tv.setVisibility(visibility);
        }
    }

    public void _setHintTvListener(View.OnClickListener listener) {
        if (mBacktoolbar != null) {
            TextView tv = mBacktoolbar.findViewById(R.id.tv_back);
            tv.setOnClickListener(listener);
        }
    }

    @Override
    public void onBackPressed() {
        ContextHandler.finish();
    }

    public void _setTitle(String str) {
        if (mBacktoolbar != null) {
            TextView tv = mBacktoolbar.findViewById(R.id.tv_title);
            tv.setVisibility(View.VISIBLE);
            tv.setText(str);
        }
    }

    public void _setTitle(int resId) {
        if (mBacktoolbar != null) {
            TextView tv = mBacktoolbar.findViewById(R.id.tv_title);
            tv.setText(resId);
        }
    }

    public void _setRight(String str, View.OnClickListener listener) {
        if (mBacktoolbar != null) {
            TextView tv = mBacktoolbar.findViewById(R.id.tv_right);
            tv.setVisibility(View.VISIBLE);
            tv.setText(str);
            tv.setOnClickListener(listener);
        }
    }

    public void _setRight(String str) {
        if (mBacktoolbar != null) {
            TextView tv = mBacktoolbar.findViewById(R.id.tv_right);
            tv.setVisibility(View.VISIBLE);
            tv.setText(str);
        }
    }

    public void _setRight(View.OnClickListener listener) {
        if (mBacktoolbar != null) {
            TextView tv = mBacktoolbar.findViewById(R.id.tv_right);
            tv.setVisibility(View.VISIBLE);
            tv.setOnClickListener(listener);
        }
    }

    public void _setTitleIcon(int visibility) {
        if (mBacktoolbar != null) {
            TextView tv = mBacktoolbar.findViewById(R.id.tv_title);
            tv.setVisibility(visibility);

            Drawable rightDrawable = getResources().getDrawable(R.mipmap.library_icon_arrow_down);
            if (visibility == View.GONE){
                rightDrawable.setBounds(0, 0, 0, 0);
            }else {
                rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
            }
            tv.setCompoundDrawables(null, null, rightDrawable, null);
        }
    }

    public String _getRight() {
        if (mBacktoolbar != null) {
            TextView tv = mBacktoolbar.findViewById(R.id.tv_right);
            tv.setVisibility(View.VISIBLE);
            return tv.getText().toString().trim();
        }
        return "";
    }

    public void _setHintRight() {
        if (mBacktoolbar != null) {
            TextView tv = mBacktoolbar.findViewById(R.id.tv_right);
            tv.setVisibility(View.GONE);
        }
    }

    public void _setRight(int resId, View.OnClickListener listener) {
        if (mBacktoolbar != null) {
            ImageView iv = mBacktoolbar.findViewById(R.id.iv_right);
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(resId);
            iv.setOnClickListener(listener);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}