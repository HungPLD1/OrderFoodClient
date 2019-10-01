package com.example.hungpld1_orderfood.view.activity;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.hungpld1_orderfood.R;
import com.example.hungpld1_orderfood.view.adapter.adapterIndicator.AdapterViewPagerIndicator;
import com.example.hungpld1_orderfood.view.fragment.listFragmentViewIndicator.FragmentFirstIndicator;
import com.example.hungpld1_orderfood.view.fragment.listFragmentViewIndicator.FragmentIndicator_End;
import com.example.hungpld1_orderfood.view.fragment.listFragmentViewIndicator.FragmentSecondIndicator;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class WaitingActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private DotsIndicator mDotsIndicator;
    private AdapterViewPagerIndicator mAdapterViewPagerIndicator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_waiting);
        getHaskey();
        initView();
        setupViewIndicator();
    }

    //get SHA1 key
    private void getHaskey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.hungpld1_orderfood",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("HungPLD1", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    private void initView() {
        mViewPager = findViewById(R.id.viewPager);
        mDotsIndicator = findViewById(R.id.dots_indicator);
    }

    private void setupViewIndicator() {
        mAdapterViewPagerIndicator = new AdapterViewPagerIndicator(getSupportFragmentManager(),getListFragmentIndicator());
        mViewPager.setAdapter(mAdapterViewPagerIndicator);
        mDotsIndicator.setViewPager(mViewPager);
    }

    private ArrayList<Fragment> getListFragmentIndicator(){
        ArrayList<Fragment> mListIndicator = new ArrayList<>();
        mListIndicator.add(new FragmentFirstIndicator());
        mListIndicator.add(new FragmentSecondIndicator());
        mListIndicator.add(new FragmentIndicator_End());
        return mListIndicator;
    }
}
