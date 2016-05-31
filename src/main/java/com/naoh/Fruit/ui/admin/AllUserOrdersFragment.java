package com.naoh.Fruit.ui.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Adapter.Adapter_ListView_SellerOrders;
import com.naoh.Fruit.Data.Data;
import com.naoh.Fruit.ui.home.DetailActivity;

/**
 * Created by Administrator on 2016/5/31.
 */
@SuppressLint("ValidFragment")
public class AllUserOrdersFragment extends Fragment implements View.OnClickListener, Adapter_ListView_SellerOrders.onCheckedChanged {

    private TextView tv_goShop, tv_cart_Allprice, tv_cart_buy_Ordel;
    private LinearLayout ll_cart;
    private ListView listView_cart;
    private CheckBox cb_cart_all;
    private Adapter_ListView_SellerOrders adapter;
    private String str_del = "处理(0)";
    private boolean[] is_choice;

    public AllUserOrdersFragment(String del) {
        str_del = del;
    }

    public AllUserOrdersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        is_choice = new boolean[Data.arrayList_cart.size()];
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_cart_all, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
//        tv_goShop = (TextView) view.findViewById(R.id.tv_goShop);
        tv_cart_Allprice = (TextView) view.findViewById(R.id.tv_cart_Allprice);
        tv_cart_buy_Ordel = (TextView) view.findViewById(R.id.tv_cart_buy_or_del);
        tv_cart_buy_Ordel.setText(str_del);
        cb_cart_all = (CheckBox) view.findViewById(R.id.cb_cart_all);
        cb_cart_all.setVisibility(View.INVISIBLE);
        tv_cart_buy_Ordel.setVisibility(View.INVISIBLE);
        tv_cart_Allprice.setVisibility(View.INVISIBLE);




        cb_cart_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                /*
                 * 判断一：（全选按钮选中）全选按钮是否选择，如果选择，那么列表每一行都选中
				 * 判断二：（全选按钮取消）当取消全选按钮时，会有两种情况
				 * ，第一：主动点击全选按钮，此时直接取消列表所有的选中状态，第二：取消列表某一行，导致全选取消，此时列表其他行仍然是选中
				 *
				 * 判断二的分析：（主动取消）判断列表每一行的选中状态，如果全部都是选中状态，那么（列表选中数=列表总数），此时属于主动取消，
				 * 则取消所有行的选中状态，反之（被动取消）则不响应
				 */

                // 记录列表每一行的选中状态数量
                int isChoice_all = 0;
                if (arg1) {
                    // 设置全选
                    for (int i = 0; i < Data.allOrderBeans.size(); i++) {
                        // 如果选中了全选，那么就将列表的每一行都选中
                        ((CheckBox) (listView_cart.getChildAt(i)).findViewById(R.id.cb_choice)).setChecked(true);
                    }
                } else {
                    // 设置全部取消
                    for (int i = 0; i < Data.allOrderBeans.size(); i++) {
                        // 判断列表每一行是否处于选中状态，如果处于选中状态，则计数+1
                        if (((CheckBox) (listView_cart.getChildAt(i)).findViewById(R.id.cb_choice)).isChecked()) {
                            // 计算出列表选中状态的数量
                            isChoice_all += 1;
                        }
                    }
                    // 判断列表选中数是否等于列表的总数，如果等于，那么就需要执行全部取消操作
                    if (isChoice_all == Data.allOrderBeans.size()) {
                        // 如果没有选中了全选，那么就将列表的每一行都不选
                        for (int i = 0; i < Data.allOrderBeans.size(); i++) {
                            // 列表每一行都取消
                            ((CheckBox) (listView_cart.getChildAt(i)).findViewById(R.id.cb_choice)).setChecked(false);
                        }
                    }
                }
            }
        });


        ll_cart = (LinearLayout) view.findViewById(R.id.ll_cart);
        listView_cart = (ListView) view.findViewById(R.id.listView_cart);
        // 如果购物车中有数据，那么就显示数据，否则显示默认界面
        if (Data.allOrderBeans != null && Data.allOrderBeans.size() != 0) {
            adapter = new Adapter_ListView_SellerOrders(getActivity(), Data.allOrderBeans);
            adapter.setOnCheckedChanged(this);
            listView_cart.setAdapter(adapter);
            ll_cart.setVisibility(View.GONE);
        } else {
            ll_cart.setVisibility(View.VISIBLE);
        }

        listView_cart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("productId", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        tv_cart_buy_Ordel.setOnClickListener(this);
//        tv_goShop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * adapter的回调函数，当点击CheckBox的时候传递点击位置和checkBox的状态
     */
    @Override
    public void getChoiceData(int position, boolean isChoice) {
        //得到点击的哪一行
        if (isChoice) {
            if (Data.allOrderBeans.size() != 0) {
                //49表示商品的价格，这里偷了下懒，没有去动态获取商品价格
                Data.Allprice_cart += 4 * 49;
            }
        } else {
            if (Data.allOrderBeans.size() != 0) {
                Data.Allprice_cart -= 3 * 49;
            }
        }
        // 记录列表处于选中状态的数量
        int num_choice = 0;
        for (int i = 0; i < Data.allOrderBeans.size(); i++) {
            // 判断列表中每一行的选中状态，如果是选中，计数加1
            if (null != listView_cart.getChildAt(i) && ((CheckBox) (listView_cart.getChildAt(i)).findViewById(R.id.cb_choice)).isChecked()) {
                // 列表处于选中状态的数量+1
                num_choice += 1;
                is_choice[i] = true;
            }
        }
        // 判断列表中的CheckBox是否全部选择
        if (num_choice == Data.allOrderBeans.size()) {
            // 如果选中的状态数量=列表的总数量，那么就将全选设置为选中
            cb_cart_all.setChecked(true);
        } else {
            // 如果选中的状态数量！=列表的总数量，那么就将全选设置为取消
            cb_cart_all.setChecked(false);
        }

        tv_cart_Allprice.setText("合计：￥" + Data.Allprice_cart + "");

        System.out.println("选择的位置--->" + position);

    }

    /**
     * 删除数组中的一个元素
     */
    public static boolean[] deleteByIndex(boolean[] array, int index) {
        boolean[] newArray = new boolean[array.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            if (i < index) {
                newArray[i] = array[i];
            } else {
                newArray[i] = array[i + 1];
            }
        }
        return newArray;
    }
}
