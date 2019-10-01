package com.example.hungpld1_orderfood.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;


import com.example.hungpld1_orderfood.R;
import com.example.hungpld1_orderfood.view.fragment.homescreen.FragmentHomeScreen;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mNavBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        initView();
        initEvent();
        setDefaultFragmentBottom(savedInstanceState);
    }

    private void initEvent() {
        mNavBottom.setOnNavigationItemSelectedListener(this);
        addFragment();
    }

    private void initView() {
        mNavBottom = findViewById(R.id.navHomeScreen);
    }

    private void addFragment() {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        FragmentHomeScreen fragmentHome = new FragmentHomeScreen();
        //add fragmnet
        mFragmentTransaction.add(R.id.containerMain,fragmentHome);
        mFragmentTransaction.commit();
    }

    private void setDefaultFragmentBottom(Bundle savedInstanceState) {
        if (savedInstanceState == null){
            mNavBottom.setSelectedItemId(R.id.home);
        }
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_menu:
                loadFragment(new FragmentHomeScreen());
                break;
        }
        return false;
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerMain, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
