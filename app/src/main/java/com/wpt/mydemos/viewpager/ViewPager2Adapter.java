package com.wpt.mydemos.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wpt
 */
public class ViewPager2Adapter extends FragmentStateAdapter {
    private ArrayList<Fragment> mFragments;
    public ViewPager2Adapter(FragmentManager fm, ArrayList<Fragment> fragments){
        super(fm,null);
        this.mFragments = fragments;
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int i) {
        return mFragments.get(i);
    }
}
