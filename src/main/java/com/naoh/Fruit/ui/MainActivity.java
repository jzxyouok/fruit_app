package com.naoh.Fruit.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.naoh.Fruit.Config;
import com.example.administrator.myapplication.R;
import com.naoh.Fruit.ui.cart.CartFragment;
import com.naoh.Fruit.ui.home.HomeFragment;
import com.naoh.Fruit.view.ChangeColorIconWithText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();
    private String[] mTitles = new String[]{"Second Fragment", "Third Fragment", "Fourth Fragment"};

    private FragmentPagerAdapter mFragmentAdapter;
    /**
     * 下面的导航栏LinearLayout
     */
    private LinearLayout mTabChat;

    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化所有的界面
        initView();
        initFragments();
        //初始化ViewPager适配器
        mFragmentAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int index) {
                return mTabs.get(index);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }
        };
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int i1) {
                if (positionOffset > 0) {
                    ChangeColorIconWithText left = mTabIndicators.get(position);
                    ChangeColorIconWithText right = mTabIndicators.get(position + 1);
                    left.setIconAlpha(1 - positionOffset);
                    right.setIconAlpha(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 初始化Fragments
     */
    private void initFragments() {
        //用于展示首页的Fragment
        HomeFragment homeFragment = new HomeFragment();
        mTabs.add(homeFragment);

        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TabFragment.TITLE, mTitles[0]);
        tabFragment.setArguments(bundle);
        mTabs.add(tabFragment);

//        TabFragment tabFragment1 = new TabFragment();
//        Bundle bundle1 = new Bundle();
//        bundle1.putString(TabFragment.TITLE, mTitles[1]);
//        tabFragment1.setArguments(bundle1);
//        mTabs.add(tabFragment1);

        CartFragment cartFragment = new CartFragment();
        mTabs.add(cartFragment);

        TabFragment tabFragment2 = new TabFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString(TabFragment.TITLE, mTitles[2]);
        tabFragment2.setArguments(bundle2);
        mTabs.add(tabFragment2);
    }

    /**
     * 初始化所有的界面
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mTabChat = (LinearLayout) findViewById(R.id.id_indicator);
        ChangeColorIconWithText one = (ChangeColorIconWithText) findViewById(R.id.id_indicator_one);
        ChangeColorIconWithText two = (ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
        ChangeColorIconWithText three = (ChangeColorIconWithText) findViewById(R.id.id_indicator_three);
        ChangeColorIconWithText four = (ChangeColorIconWithText) findViewById(R.id.id_indicator_four);

        mTabIndicators.add(one);
        mTabIndicators.add(two);
        mTabIndicators.add(three);
        mTabIndicators.add(four);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        one.setIconAlpha(Config.PURE_COLOR);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("退出应用");
            builder.setMessage("确定要退出此应用程序吗？");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //dialogInterface.dismiss();
                    MainActivity.this.finish();
                }
            });
            builder.setNegativeButton("取消", null);
            builder.create();
            builder.show();
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        resetOtherTabs();

        switch (v.getId()) {
            case R.id.id_indicator_one:
                mTabIndicators.get(Config.DEVICE_INDEX).setIconAlpha(Config.PURE_COLOR);
                mViewPager.setCurrentItem(Config.DEVICE_INDEX, false);
                break;
            case R.id.id_indicator_two:
                mTabIndicators.get(Config.ORDER_INDEX).setIconAlpha(Config.PURE_COLOR);
                mViewPager.setCurrentItem(Config.ORDER_INDEX, false);
                break;
            case R.id.id_indicator_three:
                mTabIndicators.get(Config.INFO_INDEX).setIconAlpha(Config.PURE_COLOR);
                mViewPager.setCurrentItem(Config.INFO_INDEX, false);
                break;
            case R.id.id_indicator_four:
                mTabIndicators.get(Config.SETTINGS_INDEX).setIconAlpha(Config.PURE_COLOR);
                mViewPager.setCurrentItem(Config.SETTINGS_INDEX, false);
                break;
        }
    }

    private void resetOtherTabs() {
        for (ChangeColorIconWithText changeColorIconWithText : mTabIndicators) {
            changeColorIconWithText.setIconAlpha(Config.NO_COLOR);
        }
    }

}

