package com.shangyi.kt.ui.address.weight;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.shangyi.business.R;
import com.shangyi.business.utils.StatusBarUtil;
import com.shangyi.kt.ui.address.adapter.AreaSelectAdapter;
import com.shangyi.kt.ui.address.adapter.AreaSelectHorAdapter;
import com.shangyi.kt.ui.address.bean.AreaBean;
import com.shangyi.kt.ui.address.bean.AreaItemBean;
import com.shangyi.kt.ui.address.bean.FatherData;
import com.shangyi.kt.ui.address.model.AddAddressModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * data: 2020/3/30 15:38
 * auther: Dz
 * function:
 */
public class AddressSelectDialog extends Dialog implements View.OnClickListener, AddAddressModel.OnAreaRefresh, DialogInterface.OnDismissListener {

    private Context context;
    private RecyclerView recyclerview1, recyclerview;  // 地址列表
    private AreaSelectAdapter adapter;
    private AreaSelectHorAdapter adapterHor;
    private List<String> dataHor = new ArrayList<String>();

    private AddAddressModel model;

    private Map<Integer, List<AreaItemBean>> addressData = new LinkedHashMap<>();
    private int currentitem = 0;
    private int isTopClickPosition = -1;
    private boolean isFirst = true;

    public AddressSelectDialog(@NonNull Context context) {
        this(context, R.style.CommonBottomDialogStyle);
    }

    public AddressSelectDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;

        model = new AddAddressModel();
        model.setRefreshListener(this);

        setCancelable(true);
        /**
         * 初始化资源控件id
         */
        initView();

        /**
         * 初始化数据
         */
        initData();
    }


    private void initData() {
        if (isFirst) {
            fatherMap.put("请选择", 0);
            model.getAreaData(-1, 0);
            isFirst = false;
        }
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_address_select_view, null, false);
        setContentView(view);

        //创建适配器
        recyclerview1 = findViewById(R.id.recyclerview1);
        recyclerview = findViewById(R.id.recyclerview);
//        findViewById(R.id.top_guideline).setOnClickListener(this);

        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        adapterHor = new AreaSelectHorAdapter();
        recyclerview.setAdapter(adapterHor);
        dataHor.add("请选择");
        adapterHor.replaceData(dataHor);

        adapter = new AreaSelectAdapter();
        recyclerview1.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                AreaItemBean item = (AreaItemBean) adapter.getItem(position);
                if (isTopClickPosition != -1) {
                    currentitem = isTopClickPosition;
                    switch (isTopClickPosition) {
                        case 0:
                            dataHor.clear();

                            addressData.remove(1);
                            addressData.remove(2);
                            break;
                        case 1:
                            addressData.remove(2);
                            dataHor.remove(1);
                            dataHor.remove(1);
                            break;
                    }
                    adapterHor.notifyDataSetChanged();
                    isTopClickPosition = -1;
                }

                if (item.getType() < 2) {
                    if (addressData.size() == 3) {
                        return;
                    }
                    currentitem++;
                    model.getAreaData(item.getId(), item.getType() + 1);

                    if (dataHor.size() > 0) {
                        if (dataHor.get(0).equals("请选择")) {
                            dataHor.clear();
                        }
                    }
                    if (!dataHor.contains(item.getName())){
                        dataHor.add(item.getName());
                    }

                } else if (item.getType() == 2 && dataHor.size() == 2) {
                    if (!dataHor.contains(item.getName())){
                        dataHor.add(item.getName());
                    }
                    refreshList();
                    dismiss();
                } else if (item.getType() == 2 && dataHor.size() == 3) {
                    if (!dataHor.contains(item.getName())){
                        dataHor.set(dataHor.size() - 1, item.getName());
                    }
                    refreshList();
                    dismiss();
                }
            }
        });

        adapterHor.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter _adapter, @NonNull View view, int position) {
                String item = (String) _adapter.getItem(position);

                adapterHor.setSelectItem(item);
                List<AreaItemBean> areaItemBeans = addressData.get(position);
                adapter.replaceData(areaItemBeans);

                isTopClickPosition = position;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.top_guideline:
//                dismiss();
//                break;
            default:
                //nothing
                break;
        }
    }

    public void onDestroy() {
        if (model != null) {
            model = null;
        }
    }


    private Map<String, Integer> fatherMap = new LinkedHashMap<>();

    /**
     * 设置数据
     *
     * @param area
     */
    @Override
    public void refresh(AreaBean area) {
        List<AreaItemBean> list = area.getList();
        if (!list.isEmpty()) {
            addressData.put(currentitem, list);
            adapter.replaceData(list);
            refreshList();
        }

    }

    public void refreshList() {
        adapterHor.replaceData(dataHor);
        adapterHor.setSelectItem();
        adapterHor.notifyDataSetChanged();
    }


    private OnAddressSelectListener mListener;

    public void setOnAddressSelectListener(OnAddressSelectListener listener) {
        mListener = listener;
        setOnDismissListener(this);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mListener != null) {
            if (adapterHor != null) {
                StringBuilder sb = new StringBuilder();
                for (String item : adapterHor.getData()) {
                    sb.append(item);
                }
                mListener.addressSelectListener(sb.toString());
            }
        }
    }

    public interface OnAddressSelectListener {
        void addressSelectListener(String address);
    }

}
