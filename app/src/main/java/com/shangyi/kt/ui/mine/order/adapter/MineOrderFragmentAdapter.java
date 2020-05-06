package com.shangyi.kt.ui.mine.order.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Date:2020/5/6
 * author:lwb
 * Desc:
 */
public class MineOrderFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private List<String> mTitles;

    public MineOrderFragmentAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> titles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {//必须实现
        return mFragments.get(position);
    }

    @Override
    public int getCount() {//必须实现
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {//选择性实现
        return mTitles.get(position);
    }

}
