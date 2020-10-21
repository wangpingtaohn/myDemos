package com.wpt.mydemos.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wpt
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mFragments;
    private List<String> mTitles;
    private FragmentManager mFragmentManager;
    public MyViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, List<String> titles){
        super(fm);
        this.mFragmentManager = fm;
        this.mFragments = fragments;
        this.mTitles = titles;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return  mFragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    public void setFragments(ArrayList<Fragment> mFragments) {
        this.mFragments = mFragments;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    public void setTitles(List<String> mTitles) {
        this.mTitles = mTitles;
    }
}
