package com.shangyi.kt.ui.goods;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shangyi.business.R;
import com.shangyi.business.utils.StatusBarUtil;

/**
 * data: 2020/3/30 15:38
 * auther: Dz
 * function:
 */
public class GoodsDialog extends DialogFragment implements View.OnClickListener {

    private String mId;
    private ViewPager mGoods_detail_viewpager;
    private ImageView mGoods_down_icon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = View.inflate(getActivity(),R.layout.goodsdialog_item,null);

        /**
         * 初始化资源控件id
         */
        initView(view);

        /**
         * 初始化数据
         */
        initData();

        return view;
    }

    /**
     * 设置对话框样式
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setRootViewFitsSystemWindows(getActivity(),true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(getActivity());
        //一般的手机的状态栏文字和图标都是白sa'a'a'a'a'a'a'a'a'a'a'a'a'a'a'a'a'a'a色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(getActivity(), true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(getActivity(),0x55000000);
        }
        setStyle(DialogFragment.STYLE_NORMAL, R.style.TranslucentNoTitleDialog);

    }

    private void initData() {

    }

    private void initView(View view) {
//        mGoods_detail_tab = view.findViewById(R.id.goods_detail_tab);
        //mGoods_detail_viewpager = view.findViewById(R.id.goods_detail_viewpager);
        mGoods_down_icon = view.findViewById(R.id.goods_down_icon);

        mGoods_down_icon.setOnClickListener(this);

        //创建适配器

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.goods_down_icon:
                dismiss();
                break;
            default:
                //nothing
                break;
        }
    }
}
