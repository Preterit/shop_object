package com.shangyi.kt.ui.address.weight;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.shangyi.business.R;
import com.shangyi.kt.ui.address.adapter.AreaSelectAdapter;
import com.shangyi.kt.ui.address.adapter.AreaSelectHorAdapter;
import com.shangyi.kt.ui.address.bean.AreaBean;
import com.shangyi.kt.ui.address.bean.AreaItemBean;
import com.shangyi.kt.ui.address.model.AddAddressModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    private List<AreaItemBean> dataHor = new ArrayList<>();

    private AddAddressModel model;

    private Map<Integer, List<AreaItemBean>> addressData = new LinkedHashMap<>();
    private Map<Integer, AreaItemBean> topData = new LinkedHashMap<>();

    private int currentitem = 0;  // 顶部recyclerview 选中的条目
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
        findViewById(R.id.top_guideline).setOnClickListener(this);

        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        adapterHor = new AreaSelectHorAdapter();
        recyclerview.setAdapter(adapterHor);
        dataHor.add(new AreaItemBean(0, "请选择"));
        adapterHor.replaceData(dataHor);

        adapter = new AreaSelectAdapter();
        recyclerview1.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                AreaItemBean item = (AreaItemBean) adapter.getItem(position);
                topData.put(currentitem, item);
                model.getAreaData(item.getId(), currentitem);
            }
        });

        adapterHor.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter _adapter, @NonNull View view, int position) {
                AreaItemBean item = (AreaItemBean) _adapter.getItem(position);
                currentitem = position + 1;

                adapterHor.setSelectItem(item.getName());
                List<AreaItemBean> areaItemBeans = addressData.get(currentitem);
                adapter.replaceData(areaItemBeans);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_guideline:
                dismiss();
                break;
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

    /**
     * 设置数据
     *
     * @param area
     */
    @Override
    public void refresh(AreaBean area) {
        List<AreaItemBean> list = area.getList();
        //father 如果为空表示 第一次请求，则将顶部Recyclerview数据清空
        if (area.getFather() != null) {
            dataHor.clear();
        }
        for (int i = 1; i <= topData.entrySet().size(); i++) {
            if (i <= currentitem) {
                dataHor.add(topData.get(i));
            }
        }
        refreshList();

        if (!list.isEmpty()) {
            currentitem++;
            addressData.put(currentitem, list);
            adapter.replaceData(list);
        } else {
            dismiss();
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
                List<AreaItemBean> data = adapterHor.getData();
                int id1 = 0;
                int id2 = 0;
                int id3 = 0;
                switch (data.size()) {
                    case 1:
                        sb.append(data.get(0).getName()).append("-");
                        id1 = data.get(0).getId();
                        break;
                    case 2:
                        sb.append(data.get(0).getName()).append("-");
                        id1 = data.get(0).getId();
                        sb.append(data.get(1).getName());
                        id2 = data.get(1).getId();
                        break;
                    case 3:
                        sb.append(data.get(0).getName()).append("-");
                        id1 = data.get(0).getId();
                        sb.append(data.get(1).getName()).append("-");
                        id2 = data.get(1).getId();
                        sb.append(data.get(2).getName());
                        id3 = data.get(2).getId();
                        break;
                }
                mListener.addressSelectListener(sb.toString(), id1, id2, id3);
            }
        }
    }

    public interface OnAddressSelectListener {
        void addressSelectListener(String address, int id1, int id2, int id3);
    }

    public boolean isInList(AreaItemBean item) {
        for (AreaItemBean bean : dataHor) {
            if (bean.getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }

}
