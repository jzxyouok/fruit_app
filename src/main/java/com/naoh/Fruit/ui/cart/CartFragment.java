package com.naoh.Fruit.ui.cart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naoh.Fruit.Data.Data;
import com.example.administrator.myapplication.R;

/**
 * Created by NaOH on 2016/4/15.
 */
public class CartFragment extends Fragment implements View.OnClickListener {

    private TextView bt_cart_all, bt_cart_low, bt_cart_stock, bt_cart_edit;

    private View show_cart_all, show_cart_low, show_cart_stock;

    private AllBabyFragment allBabyFragment;
    private LowBabyFragment lowBabyFragment;
    private StockBabyFragment stockBabyFragment;
    private boolean isDel=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_cart, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ((TextView) view.findViewById(R.id.tv_top_txtTitle)).setText("我的购物车");

        bt_cart_all = (TextView) view.findViewById(R.id.bt_cart_all);
        bt_cart_low = (TextView) view.findViewById(R.id.bt_cart_low);
        bt_cart_stock = (TextView) view.findViewById(R.id.bt_cart_stock);
        bt_cart_edit = (TextView) view.findViewById(R.id.tv_top_edit);

        show_cart_all = view.findViewById(R.id.show_cart_all);
        show_cart_low = view.findViewById(R.id.show_cart_low);
        show_cart_stock = view.findViewById(R.id.show_cart_stock);

        bt_cart_all.setOnClickListener(this);
        bt_cart_low.setOnClickListener(this);
        bt_cart_stock.setOnClickListener(this);
        bt_cart_edit.setOnClickListener(this);

        allBabyFragment = new AllBabyFragment();
        addFragment(allBabyFragment);
        showFragment(allBabyFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_top_edit:
                if (allBabyFragment !=null&&isDel) {
                    removeFragment(allBabyFragment);
                    allBabyFragment =null;
                    allBabyFragment =new AllBabyFragment("删除");
                    addFragment(allBabyFragment);
                    showFragment(allBabyFragment);
                    isDel=false;
                    bt_cart_edit.setText("完成");
                    Data.Allprice_cart=0;

                }else if (!isDel&& allBabyFragment !=null) {
                    removeFragment(allBabyFragment);
                    allBabyFragment =null;
                    allBabyFragment =new AllBabyFragment();
                    addFragment(allBabyFragment);
                    showFragment(allBabyFragment);
                    isDel=true;
                    Data.Allprice_cart=0;
                    bt_cart_edit.setText("编辑");
                }
                break;
            case R.id.bt_cart_all:
                if (allBabyFragment == null) {
                    allBabyFragment = new AllBabyFragment();
                    addFragment(allBabyFragment);
                    showFragment(allBabyFragment);
                } else {
                    showFragment(allBabyFragment);
                }
                show_cart_all.setBackgroundColor(getResources().getColor(R.color.black));
                show_cart_low.setBackgroundColor(getResources().getColor(R.color.bg_Gray));
                show_cart_stock.setBackgroundColor(getResources().getColor(R.color.bg_Gray));
                break;
            case R.id.bt_cart_low:
                if (lowBabyFragment == null) {
                    lowBabyFragment = new LowBabyFragment();
                    addFragment(lowBabyFragment);
                    showFragment(lowBabyFragment);
                } else {
                    showFragment(lowBabyFragment);
                }
                show_cart_low.setBackgroundColor(getResources().getColor(R.color.black));
                show_cart_all.setBackgroundColor(getResources().getColor(R.color.bg_Gray));
                show_cart_stock.setBackgroundColor(getResources().getColor(R.color.bg_Gray));

                break;
            case R.id.bt_cart_stock:
                if (stockBabyFragment == null) {
                    stockBabyFragment = new StockBabyFragment();
                    addFragment(stockBabyFragment);
                    showFragment(stockBabyFragment);
                } else {
                    showFragment(stockBabyFragment);
                }
                show_cart_stock.setBackgroundColor(getResources().getColor(R.color.black));
                show_cart_all.setBackgroundColor(getResources().getColor(R.color.bg_Gray));
                show_cart_low.setBackgroundColor(getResources().getColor(R.color.bg_Gray));

                break;

            default:
                break;
        }
    }

    /** 添加Fragment **/
    public void addFragment(Fragment fragment) {
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.add(R.id.show_cart_view, fragment);
        ft.commitAllowingStateLoss();
    }
    /** 删除Fragment **/
    public void removeFragment(Fragment fragment) {
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commitAllowingStateLoss();
    }

    /** 显示Fragment **/
    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        if (allBabyFragment != null) {
            ft.hide(allBabyFragment);
        }
        if (lowBabyFragment != null) {
            ft.hide(lowBabyFragment);
        }
        if (stockBabyFragment != null) {
            ft.hide(stockBabyFragment);
        }

        ft.show(fragment);
        ft.commitAllowingStateLoss();

    }
}
