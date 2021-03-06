package com.shangyi.kt.fragment.mine.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Date:2020/4/28
 * author:lwb
 * Desc:
 */
public class MineFragmentAdapter extends FragmentPagerAdapter {
    private List<MineTjDataFragment> mFragments;

    public MineFragmentAdapter(FragmentManager fm, List<MineTjDataFragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
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
        return mFragments.get(position).getClass().getSimpleName();
    }
}