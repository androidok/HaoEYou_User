package com.haoeyou.user.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/3/8.
 */
public class DataCompareFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private List<Fragment> mFragments;

    public DataCompareFragmentAdapter(FragmentManager fm, String[] tabTitles, List<Fragment> mFragments) {
        super(fm);
        this.mTitles = tabTitles;
        this.mFragments = mFragments;
    }


    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        
        return mFragments.get(position);
    }
}
