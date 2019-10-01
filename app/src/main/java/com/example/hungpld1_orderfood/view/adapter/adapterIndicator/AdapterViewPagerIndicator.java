package com.example.hungpld1_orderfood.view.adapter.adapterIndicator;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPagerIndicator extends FragmentPagerAdapter {
    private List<Fragment> mListFragment;

    public AdapterViewPagerIndicator(FragmentManager fm, ArrayList<Fragment> arrFragment) {
        super(fm);
        this.mListFragment = arrFragment;
    }


    @Override
    public int getCount() {
        return mListFragment.size();
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return mListFragment.get(i);
    }
}
