package com.shangyi.kt.ui.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.sdxxtop.webview.utils.WebConstants;
import com.shangyi.business.R;
import com.shangyi.business.weight.CumAboutItemView;
import com.shangyi.kt.ui.WebActivity;
import com.shangyi.kt.ui.userlogin.LoginActivity;

/**
 * 关于上医宝库相关页面
 */
public class AboutSyActivity extends AppCompatActivity implements View.OnClickListener{

    private CumAboutItemView mAboutFw;
    private CumAboutItemView mAboutYs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_sy);

        mAboutFw = findViewById(R.id.about_fw);
        mAboutYs = findViewById(R.id.about_ys);

        mAboutFw.setOnClickListener(this);
        mAboutYs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.about_fw://服务协议
                WebActivity.startCommonWeb(AboutSyActivity.this, "上医宝库平台用户协议", "http://39.106.156.132/service.html", WebConstants.LEVEL_BASE);
                break;
            case R.id.about_ys://隐私政策
                WebActivity.startCommonWeb(AboutSyActivity.this, "上医宝库隐私政策", "http://39.106.156.132/privacy.html", WebConstants.LEVEL_BASE);
                break;
            default:
                //nothing
                break;
        }
    }
}
