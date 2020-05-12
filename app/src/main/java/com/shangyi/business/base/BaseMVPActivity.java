package com.shangyi.business.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shangyi.business.R;
import com.shangyi.business.utils.StatusBarUtil;

/**
 * data: 2020/3/17 17:36
 * auther: Dz
 * function:
 */
public abstract class BaseMVPActivity<V,P extends BaseMVPPresenter> extends AppCompatActivity {

    public P presenter;
    private MyReceive mMyReceive;
    private ErrorView mErrorView;
    private Toolbar mBacktoolbar;
    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里注意下 因为在评论区发现有网友调用setRootViewFitsSystemWindows 里面 winContent.getChildCount()=0 导致代码无法继续
        //是因为你需要在setContentView之后才可以调用 setRootViewFitsSystemWindows

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白sa'a'a'a'a'a'a'a'a'a'a'a'a'a'a'a'a'a'a色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this,0x55000000);
        }
        ContextHandler.addActivity(this);

        mErrorView = new ErrorView(this);
        mErrorView.setMinimumHeight(50);
        mErrorView.setVisibility(View.INVISIBLE);
        ((ViewGroup)getWindow().getDecorView()).addView(mErrorView);
        setContentView(setView());
        presenter = initPresenter();
        mBacktoolbar = findViewById(R.id.toolbar);
        setBack(null);
        if (!isConnNet()){
            showToast("亲~您的手机没有网路了哟");
            Intent intent = new Intent(this,NetWorkActivity.class);
            startActivity(intent);
            finish();
        }else {
            initView();
            initData();
            setListener();
        }

        initView();
        initReceivce();
    }


    protected abstract void initView();
    protected abstract void initData();
    protected abstract void setListener();

    private boolean isReceivce = false;
    public void initReceivce(){
        if (isReceivce){
            mMyReceive = new MyReceive();
            MyReceive myReceive = mMyReceive;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            registerReceiver(myReceive,intentFilter);
        }
    }


    protected abstract int setView();
    protected abstract P initPresenter();
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

    class MyReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!isConnNet()){
                mErrorView.setVisibility(View.VISIBLE);
            }else {
                mErrorView.setVisibility(View.INVISIBLE);
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
     * 判断权限是否已经配置了，
     *     String...    意思是可以传入多个字符串
     * @param permissions
     * @return
     */
    public boolean hasPermissions(String... permissions){
        for (String permission : permissions){
            if (ContextCompat.checkSelfPermission(this,permission)
                    != PackageManager.PERMISSION_GRANTED){
                return true;
            }
        }
        return false;
    }

    /**
     * 若SD卡写的权限已经存在，执行以下方法
     */
    public void doSDCard() {}

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attch(this);
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
        presenter.detach();
        presenter  = null;
        if(isReceivce) {
            isReceivce = false;
            unregisterReceiver(mMyReceive);
            mMyReceive = null;
        }
    }
}
