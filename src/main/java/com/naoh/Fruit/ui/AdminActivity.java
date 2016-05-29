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

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Config;
import com.naoh.Fruit.ui.admin.AllOrdersFragment;
import com.naoh.Fruit.ui.admin.MyProductsFragment;
import com.naoh.Fruit.ui.cart.CartFragment;
import com.naoh.Fruit.ui.home.HomeFragment;
import com.naoh.Fruit.view.ChangeColorIconWithText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/2.
 */

public class AdminActivity extends FragmentActivity implements View.OnClickListener{

    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();
    private String[] mTitles = new String[]{"所有订单", "已发布的商品", "发布新商品"};
    private FragmentPagerAdapter mFragmentAdapter;

    /**
     * 下面的导航栏LinearLayout
     */
    private LinearLayout mTabChat;

    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_admin2);

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
        AllOrdersFragment ordersFragment = new AllOrdersFragment();
        mTabs.add(ordersFragment);

        MyProductsFragment productsFragment = new MyProductsFragment();

//        TabFragment tabFragment = new TabFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(TabFragment.TITLE, mTitles[1]);
//        tabFragment.setArguments(bundle);
        mTabs.add(productsFragment);


        /**
         * TODO:修改成管理员的查看个人信息，历史订单信息的
         */
        CartFragment cartFragment = new CartFragment();
        mTabs.add(cartFragment);
    }

    /**
     * 初始化所有的界面
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.admin_viewpager);
        mTabChat = (LinearLayout) findViewById(R.id.admin_indicator);
        ChangeColorIconWithText one = (ChangeColorIconWithText) findViewById(R.id.admin_indicator_one);
        ChangeColorIconWithText two = (ChangeColorIconWithText) findViewById(R.id.admin_indicator_two);
        ChangeColorIconWithText three = (ChangeColorIconWithText) findViewById(R.id.admin_indicator_three);

        mTabIndicators.add(one);
        mTabIndicators.add(two);
        mTabIndicators.add(three);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);

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
                    AdminActivity.this.finish();
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
            case R.id.admin_indicator_one:
                mTabIndicators.get(Config.DEVICE_INDEX).setIconAlpha(Config.PURE_COLOR);
                mViewPager.setCurrentItem(Config.DEVICE_INDEX, false);
                break;
            case R.id.admin_indicator_two:
                mTabIndicators.get(Config.ORDER_INDEX).setIconAlpha(Config.PURE_COLOR);
                mViewPager.setCurrentItem(Config.ORDER_INDEX, false);
                break;
            case R.id.admin_indicator_three:
                mTabIndicators.get(Config.INFO_INDEX).setIconAlpha(Config.PURE_COLOR);
                mViewPager.setCurrentItem(Config.INFO_INDEX, false);
                break;
        }
    }

    private void resetOtherTabs() {
        for (ChangeColorIconWithText changeColorIconWithText : mTabIndicators) {
            changeColorIconWithText.setIconAlpha(Config.NO_COLOR);
        }
    }
}

//public class AdminActivity extends Activity implements View.OnClickListener{
//
//    private EditText editTextP = null;
//    private EditText editTextN = null;
//    private EditText editTextF = null;
//    private TextView attempts;
//    private Button sure,drop;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_admin);
//        editTextP = (EditText) findViewById(R.id.editTextP);
//        editTextN = (EditText) findViewById(R.id.editTextN);
//        editTextF = (EditText) findViewById(R.id.editTextF);
////        attempts = (TextView) findViewById(R.id.textView5);
//        sure = (Button)findViewById(R.id.buttonS);
//        drop = (Button)findViewById(R.id.buttonD);
//        sure.setOnClickListener(this);
//        drop.setOnClickListener(this);
//        // attempts.setText(Integer.toString(counter));
//    }
//
//
//    @Override
//    public void onClick(View arg0) {
//        switch(arg0.getId()){
//            case R.id.buttonS:
//                  makesure();
//                break;
//            case R.id.buttonD:
//                break;
//
//        }
//
//    }
//
//    private void makesure() {
//    }
//}
