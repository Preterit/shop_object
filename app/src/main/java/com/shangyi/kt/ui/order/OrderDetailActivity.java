package com.shangyi.kt.ui.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.shangyi.business.R;
import com.shangyi.kt.ui.mine.order.adapter.OrderListFragmentAdapter;

public class OrderDetailActivity extends AppCompatActivity {

    private ImageView mBackImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initData();
        initView();
        initEvent();
    }

    private void initData(){
        Intent intent = getIntent();
        if (intent != null) {
            int oid = intent.getIntExtra(OrderListFragmentAdapter.ORDER_LIST_ID_BUNDLE_KEY, 0);
            if (oid > 0) {
                return;
            }
        }
        Toast.makeText(OrderDetailActivity.this, "页面初始化失败，请重试", Toast.LENGTH_LONG).show();
        finish();
    }

    private void initView(){
        mBackImg = findViewById(R.id.order_detail_back_img);
    }

    private void initEvent(){
        mBackImg.setOnClickListener(v -> finish());
    }

}
