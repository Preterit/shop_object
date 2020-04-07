package com.shangyi.kt.ui;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sdxxtop.base.utils.StatusBarUtil;
import com.sdxxtop.webview.AccountWebFragment;
import com.sdxxtop.webview.CommonWebFragment;
import com.sdxxtop.webview.basefragment.BaseWebviewFragment;
import com.sdxxtop.webview.command.Command;
import com.sdxxtop.webview.command.CommandsManager;
import com.sdxxtop.webview.command.ResultBack;
import com.sdxxtop.webview.utils.WebConstants;
import com.shangyi.business.R;
import com.shangyi.business.weight.TitleView;

import java.util.HashMap;
import java.util.Map;

public class WebActivity extends AppCompatActivity {
    private String title;
    private String url;

    BaseWebviewFragment webviewFragment;

    public static void startCommonWeb(Context context, String title, String url, int testLevel) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WebConstants.INTENT_TAG_TITLE, title);
        intent.putExtra(WebConstants.INTENT_TAG_URL, url);
        intent.putExtra("level", testLevel);
        if (context instanceof Service) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    public static void startAccountWeb(Context context, String title, String url, int testLevel, @NonNull HashMap<String, String> headers) {
        Intent intent = new Intent(context, WebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(WebConstants.INTENT_TAG_TITLE, title);
        bundle.putString(WebConstants.INTENT_TAG_URL, url);
        bundle.putSerializable(WebConstants.INTENT_TAG_HEADERS, headers);
        bundle.putInt("level", testLevel);
        if (context instanceof Service) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initStatusBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        title = getIntent().getStringExtra(WebConstants.INTENT_TAG_TITLE);
        url = getIntent().getStringExtra(WebConstants.INTENT_TAG_URL);
        TitleView titlView = findViewById(R.id.titleView);
        titlView.setTitleValue(title);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        CommandsManager.getInstance().registerCommand(WebConstants.LEVEL_LOCAL, titleUpdateCommand);
        int level = getIntent().getIntExtra("level", WebConstants.LEVEL_BASE);
        webviewFragment = null;
        if (level == WebConstants.LEVEL_BASE) {
            webviewFragment = CommonWebFragment.newInstance(url);
        } else {
            webviewFragment = AccountWebFragment.newInstance(url, (HashMap<String, String>) getIntent().getExtras().getSerializable(WebConstants.INTENT_TAG_HEADERS), true);
        }
        transaction.replace(R.id.web_view_fragment, webviewFragment).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webviewFragment != null && webviewFragment instanceof BaseWebviewFragment) {
            boolean flag = webviewFragment.onKeyDown(keyCode, event);
            if (flag) {
                return flag;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 页面路由
     */
    private final Command titleUpdateCommand = new Command() {
        @Override
        public String name() {
            return Command.COMMAND_UPDATE_TITLE;
        }

        @Override
        public void exec(Context context, Map params, ResultBack resultBack) {
            if (params.containsKey(Command.COMMAND_UPDATE_TITLE_PARAMS_TITLE)) {
                setTitle((String) params.get(Command.COMMAND_UPDATE_TITLE_PARAMS_TITLE));
            }
        }
    };

    /**
     * statusBar 控制
     */
    public void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (StatusBarUtil.MIUISetStatusBarLightMode(getWindow(), true)) { //小米MIUI系统
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //Android6.0以上系统
                    StatusBarUtil.android6_SetStatusBarLightMode(getWindow());
                    StatusBarUtil.compat(this);
                } else {
                    StatusBarUtil.compat(this);
                }
            } else if (StatusBarUtil.FlymeSetStatusBarLightMode(getWindow(), true)) { //魅族flyme系统
                StatusBarUtil.compat(this);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //Android6.0以上系统
                StatusBarUtil.android6_SetStatusBarLightMode(getWindow());
                StatusBarUtil.compat(this);
            }
        }
    }
}
