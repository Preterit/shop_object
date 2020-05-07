package com.shangyi.business.weight.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.shangyi.business.R;
import com.shangyi.business.utils.LogUtils;
import com.shangyi.business.weight.dialog.adapter.YhqFragmentDialogAdapter;
import com.shangyi.kt.ui.order.AffirmOrderActivity;
import com.shangyi.kt.ui.order.bean.OrderListJsonBean;
import com.shangyi.kt.ui.order.model.CommitOrderModel;

import java.util.ArrayList;

/**
 * Date:2020/5/5
 * author:lwb
 * Desc:
 */
public class YhqDialog extends DialogFragment implements CommitOrderModel.OnYhqLoad, OnItemClickListener {

    private RecyclerView recycler;
    private CommitOrderModel orderModel;
    private YhqFragmentDialogAdapter adapter;

    public YhqDialog() {
    }

    public static YhqDialog newInstance(ArrayList<OrderListJsonBean> data) {
        YhqDialog fragment = new YhqDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_yhu_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recycler = view.findViewById(R.id.recyclerview);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new YhqFragmentDialogAdapter();
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        Bundle arguments = getArguments();
        ArrayList<OrderListJsonBean> data = (ArrayList<OrderListJsonBean>) arguments.getSerializable("data");
        orderModel.getYhqList(data);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = (height / 3) * 2;
        Window window = getDialog().getWindow();
        window.setAttributes((WindowManager.LayoutParams) params);
        window.setGravity(Gravity.BOTTOM);

        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialogStyleBottom); //dialog全屏
        orderModel = new CommitOrderModel();
        orderModel.setYhqListener(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        orderModel = null;
    }

    /**
     * 获取优惠券列表的回掉。
     */
    @Override
    public void yhqList() {
        LogUtils.e("获取优惠券列表的回掉。");
    }

    /**
     * 适配器的点击事件
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        if (getActivity() instanceof AffirmOrderActivity) {
            AffirmOrderActivity activity = (AffirmOrderActivity) getActivity();
            activity.selectYhq();
        }
    }
}