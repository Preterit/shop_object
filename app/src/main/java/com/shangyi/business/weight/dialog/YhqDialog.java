package com.shangyi.business.weight.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shangyi.business.R;
import com.shangyi.business.weight.dialog.adapter.YhqFragmentDialogAdapter;

/**
 * Date:2020/5/5
 * author:lwb
 * Desc:
 */
public class YhqDialog extends DialogFragment {

    private RecyclerView recycler;

    public YhqDialog() {
    }

    public static YhqDialog newInstance(String tittle) {
        YhqDialog fragment = new YhqDialog();
        Bundle bundle = new Bundle();
        bundle.putString("tittle", tittle);
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
        recycler.setAdapter(new YhqFragmentDialogAdapter());
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
        super.onCreate(savedInstanceState);
    }
}
