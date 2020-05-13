package com.shangyi.business.weight.dialog;

import android.content.Context;
import android.content.DialogInterface;
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
import com.sdxxtop.base.utils.UIUtils;
import com.shangyi.business.R;
import com.shangyi.business.weight.dialog.adapter.OrderCancelAdapter;
import com.shangyi.business.weight.dialog.adapter.RefundOrderAdapter;
import com.shangyi.kt.ui.mine.order.model.OrderListFragmentModel;

/**
 * Date:2020/5/5
 * author:lwb
 * Desc:
 */
public class RefundOrderDialog extends DialogFragment {

    private RecyclerView recycler;
    private OrderListFragmentModel orderModel;
    private RefundOrderAdapter adapter;

    public RefundOrderDialog() {
    }

    public static RefundOrderDialog newInstance() {
        RefundOrderDialog dialog = new RefundOrderDialog();
        Bundle bundle = new Bundle();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_refund_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recycler = view.findViewById(R.id.recyclerview);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RefundOrderAdapter();
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> _adapter, @NonNull View view, int position) {
                String item = (String) _adapter.getItem(position);
                if (mListener != null) {
                    mListener.onItemClick(item);
                    adapter.setCurrentSelectItem(position);
                }
            }
        });

        view.findViewById(R.id.tvClose).setOnClickListener(v -> dismiss());
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
        orderModel = new OrderListFragmentModel();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        orderModel = null;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mListener != null) {
            mListener.onDismiss();
        }
    }

    public interface OnDissListener {
        void onDismiss();

        void onItemClick(String str);

    }

    private OnDissListener mListener;

    public void setOnDismiss(OnDissListener listener) {
        this.mListener = listener;
    }
}
