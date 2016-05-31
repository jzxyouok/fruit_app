package com.naoh.Fruit.ui.admin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Data.Data;

/**
 * Created by Administrator on 2016/5/31.
 */
public class SellerOrdersFragment extends Fragment implements View.OnClickListener {

    private TextView bt_orders_all, bt_handled_orders, bt_unhandled_orders, bt_order_edit;

    private View show_orders_all, show_handled_orders, show_unhandled_order;

    private AllUserOrdersFragment allUserOrderFragment;
    private HandledOrderFragment handledOrderFragment;
    private UnHandledOrderFragment unHandledOrderFragment;

    private boolean isDel=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_seller_orders, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ((TextView) view.findViewById(R.id.tv_top_txtTitle)).setText("商家中心");
        bt_orders_all = (TextView) view.findViewById(R.id.bt_all_orders);
        bt_handled_orders = (TextView) view.findViewById(R.id.bt_handled_order);
        bt_unhandled_orders = (TextView) view.findViewById(R.id.bt_unhandled_order);
        bt_order_edit = (TextView) view.findViewById(R.id.tv_top_edit);

        show_orders_all = view.findViewById(R.id.show_all_order);
        show_handled_orders = view.findViewById(R.id.show_handled_order);
        show_unhandled_order = view.findViewById(R.id.show_unhandled_order);

        bt_orders_all.setOnClickListener(this);
        bt_handled_orders.setOnClickListener(this);
        bt_unhandled_orders.setOnClickListener(this);
        bt_order_edit.setOnClickListener(this);

        allUserOrderFragment = new AllUserOrdersFragment();
        addFragment(allUserOrderFragment);
        showFragment(allUserOrderFragment);
    }


    @Override
    public void onClick(View v) {
        Log.i("SellersOrder", String.valueOf(v.getId()));
        switch (v.getId()) {
            case R.id.tv_top_edit:
                if (allUserOrderFragment !=null&&isDel) {
                    removeFragment(allUserOrderFragment);
                    allUserOrderFragment =null;
                    allUserOrderFragment =new AllUserOrdersFragment("处理");
                    addFragment(allUserOrderFragment);
                    showFragment(allUserOrderFragment);
                    isDel=false;
                    bt_order_edit.setText("完成");
                    Data.Allprice_cart=0;

                }else if (!isDel&& allUserOrderFragment !=null) {
                    removeFragment(allUserOrderFragment);
                    allUserOrderFragment =null;
                    allUserOrderFragment =new AllUserOrdersFragment();
                    addFragment(allUserOrderFragment);
                    showFragment(allUserOrderFragment);
                    isDel=true;
                    Data.Allprice_cart=0;
                    bt_order_edit.setText("编辑");
                }
                break;
            case R.id.bt_all_orders:
                if (allUserOrderFragment == null) {
                    allUserOrderFragment = new AllUserOrdersFragment();
                    addFragment(allUserOrderFragment);
                    showFragment(allUserOrderFragment);
                } else {
                    showFragment(allUserOrderFragment);
                }

                show_orders_all.setBackgroundColor(getResources().getColor(R.color.black));
                show_handled_orders.setBackgroundColor(getResources().getColor(R.color.bg_Gray));
                show_unhandled_order.setBackgroundColor(getResources().getColor(R.color.bg_Gray));
                break;
            case R.id.bt_handled_order:
                if (handledOrderFragment == null) {
                    handledOrderFragment = new HandledOrderFragment("处理");
                    addFragment(handledOrderFragment);
                    showFragment(handledOrderFragment);
                } else {
                    showFragment(handledOrderFragment);
                }
                show_handled_orders.setBackgroundColor(getResources().getColor(R.color.black));
                show_orders_all.setBackgroundColor(getResources().getColor(R.color.bg_Gray));
                show_unhandled_order.setBackgroundColor(getResources().getColor(R.color.bg_Gray));

                break;
            case R.id.bt_unhandled_order:
                if (unHandledOrderFragment == null) {
                    unHandledOrderFragment = new UnHandledOrderFragment();
                    addFragment(unHandledOrderFragment);
                    showFragment(unHandledOrderFragment);
                } else {
                    showFragment(unHandledOrderFragment);
                }
                show_unhandled_order.setBackgroundColor(getResources().getColor(R.color.black));
                show_orders_all.setBackgroundColor(getResources().getColor(R.color.bg_Gray));
                show_handled_orders.setBackgroundColor(getResources().getColor(R.color.bg_Gray));

                break;

            default:
                break;
        }

    }

    /** 添加Fragment **/
    public void addFragment(Fragment fragment) {
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.add(R.id.show_order_view, fragment);
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
        if (allUserOrderFragment != null) {
            ft.hide(allUserOrderFragment);
        }
        if (handledOrderFragment != null) {
            ft.hide(handledOrderFragment);
        }
        if (unHandledOrderFragment != null) {
            ft.hide(unHandledOrderFragment);
        }

        ft.show(fragment);
        ft.commitAllowingStateLoss();

    }
}
