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
import com.naoh.Fruit.Data.OrderBean;
import com.naoh.Fruit.ui.home.DetailActivity;

/**
 * Created by Administrator on 2016/5/31.
 */
@SuppressLint("ValidFragment")
public class HandledOrderFragment extends Fragment implements View.OnClickListener, Adapter_ListView_SellerOrders.onCheckedChanged {

    private TextView tv_goShop, tv_cart_Allprice, tv_cart_buy_Ordel;
    private LinearLayout ll_cart;
    private ListView listView_cart;
    private CheckBox cb_cart_all;
    private Adapter_ListView_SellerOrders adapter;
    private String str_del = "处理(0)";
    private boolean[] is_choice;


    public HandledOrderFragment(String del) {
        str_del = del;
    }

    public HandledOrderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        is_choice = new boolean[Data.unHandledOrders.size()];
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




        ll_cart = (LinearLayout) view.findViewById(R.id.ll_cart);
        listView_cart = (ListView) view.findViewById(R.id.listView_cart);
        // 如有数据，那么就显示数据，否则显示默认界面
        if (Data.handledOrders != null && Data.handledOrders.size() != 0) {
            adapter = new Adapter_ListView_SellerOrders(getActivity(), Data.handledOrders);
            adapter.setOnCheckedChanged(this);
            listView_cart.setAdapter(adapter);
//            for(int i=0; i<Data.handledOrders.size(); i++) {
//                listView_cart.getChildAt(i).findViewById(R.id.cb_choice).setVisibility(View.INVISIBLE);
//            }
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
            if (Data.handledOrders.size() != 0) {
                //49表示商品的价格，这里偷了下懒，没有去动态获取商品价格
                Data.Allprice_cart += 4 * 49;
            }
        } else {
            if (Data.handledOrders.size() != 0) {
                Data.Allprice_cart -= 3 * 49;
            }
        }
        // 记录列表处于选中状态的数量
        int num_choice = 0;
        for (int i = 0; i < Data.handledOrders.size(); i++) {
            // 判断列表中每一行的选中状态，如果是选中，计数加1
            if (null != listView_cart.getChildAt(i) && ((CheckBox) (listView_cart.getChildAt(i)).findViewById(R.id.cb_choice)).isChecked()) {
                // 列表处于选中状态的数量+1
                num_choice += 1;
                is_choice[i] = true;
            }
        }
        // 判断列表中的CheckBox是否全部选择
        if (num_choice == Data.handledOrders.size()) {
            // 如果选中的状态数量=列表的总数量，那么就将全选设置为选中
            cb_cart_all.setChecked(true);
        } else {
            // 如果选中的状态数量！=列表的总数量，那么就将全选设置为取消
            cb_cart_all.setChecked(false);
        }

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