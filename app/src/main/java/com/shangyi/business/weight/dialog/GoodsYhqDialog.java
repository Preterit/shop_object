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

import com.shangyi.business.R;
import com.shangyi.business.weight.dialog.adapter.GoodsYhqDialogAdapter;
import com.shangyi.kt.ui.goods.bean.YouhuiquanBean;
import com.shangyi.kt.ui.goods.model.GoodDetailModel;

import java.util.ArrayList;

/**
 * Date:2020/5/5
 * author:lwb
 * Desc:
 */
public class GoodsYhqDialog extends DialogFragment {

    private RecyclerView recycler;
    private GoodDetailModel orderModel;
    private GoodsYhqDialogAdapter adapter;

    public GoodsYhqDialog() {
    }

    public static GoodsYhqDialog newInstance(ArrayList<YouhuiquanBean> data) {
        GoodsYhqDialog fragment = new GoodsYhqDialog();
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
        adapter = new GoodsYhqDialogAdapter();
        adapter.setOnGetClickListener(id -> getYhq(id));
        recycler.setAdapter(adapter);
        view.findViewById(R.id.tvCancel).setOnClickListener(v -> dismiss());

        Bundle arguments = getArguments();
        ArrayList<YouhuiquanBean> data = (ArrayList<YouhuiquanBean>) arguments.getSerializable("data");
        adapter.setList(data);
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 领取优惠券
     *
     * @param id
     */
    private void getYhq(int id) {
        if (orderModel != null) {
            orderModel.getYhqData(id);
        }
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
        orderModel = new GoodDetailModel();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        orderModel = null;
    }
}
