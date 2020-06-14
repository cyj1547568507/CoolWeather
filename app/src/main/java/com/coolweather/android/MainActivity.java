package com.coolweather.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private Fragment mFragment;
    private HomeFragment homeFragment;
    private ChooseAreaFragment chooseAreaFragment;
    private FragmentTransaction transaction;
    BottomNavigationBar bottomNavigationBar;


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initFragment();
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        BottomNavigationBar();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        chooseAreaFragment = new ChooseAreaFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container,homeFragment).commit();
        mFragment = homeFragment;
    }

    private void BottomNavigationBar(){
        bottomNavigationBar.setActiveColor(R.color.colorAccent)
                .setInActiveColor(R.color.colorPrimary)
                .setBarBackgroundColor("#FFFFFF");
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //背景样式
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home, "Home").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.tianqi, "Weather").setActiveColorResource(R.color.colorAccent))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch(position){
            case 0:
                switchFragment(homeFragment);
                break;
            case 1:
                switchFragment(chooseAreaFragment);
                break;
            case 2:
                switchFragment(homeFragment);
                break;
            default:
                switchFragment(homeFragment);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if(mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.fragment_container,fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }
}
