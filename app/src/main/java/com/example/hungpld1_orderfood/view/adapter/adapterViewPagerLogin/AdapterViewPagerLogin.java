package com.example.hungpld1_orderfood.view.adapter.adapterViewPagerLogin;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.hungpld1_orderfood.view.fragment.listFragmentViewIndicator.FragmentLogin;
import com.example.hungpld1_orderfood.view.fragment.listFragmentViewIndicator.FragmentRegister;


public class AdapterViewPagerLogin extends FragmentPagerAdapter {


    public AdapterViewPagerLogin(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }



    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Login";
            case 1:
                return "Register";
        }
        return super.getPageTitle(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentLogin mFragmentLogin = new FragmentLogin();
                return mFragmentLogin;
            case 1:
                FragmentRegister mFragmentRegister = new FragmentRegister();
                return mFragmentRegister;
        }
        return null;
    }
}
