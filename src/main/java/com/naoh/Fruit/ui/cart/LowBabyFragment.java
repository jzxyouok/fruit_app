package com.naoh.Fruit.ui.cart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Adapter.Adapter_ListView_LowBaby;
import com.naoh.Fruit.Data.ProductBean;
import com.naoh.Fruit.Data.ProductPriceReductionRecord;
import com.naoh.Fruit.dao.ProductService;

import java.util.List;

/**
 * Created by NaOH on 2016/4/18.
 */
public class LowBabyFragment extends Fragment implements View.OnClickListener, Adapter_ListView_LowBaby.onCheckedChanged {

    private LinearLayout low_baby_cart;
    private ListView listView_lowbaby_cart;
    private Adapter_ListView_LowBaby adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_lowbaby, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        low_baby_cart = (LinearLayout) view.findViewById(R.id.low_baby_cart);
        listView_lowbaby_cart = (ListView) view.findViewById(R.id.listView_low_baby_cart);

        List<ProductPriceReductionRecord> babies = new ProductService(getActivity()).findReductionProduction();

        if(babies.size()==0){
            ProductPriceReductionRecord ppr = new ProductPriceReductionRecord();
            ProductBean productBean = new ProductBean();
            productBean.setId(34);
            productBean.setName("精品菠萝");
            ppr.setReductionRecordId(34);
            ppr.setOriginPrice(24.0);
            ppr.setCurrentPrice(15.5);
            ppr.setProductBean(productBean);
            babies.add(ppr);
        }

        // 如果有降价商品，那么就显示数据，否则显示默认界面
        if (babies != null && babies.size() != 0) {
            adapter = new Adapter_ListView_LowBaby(getActivity(), babies);
            adapter.setOnCheckedChanged(this);
            listView_lowbaby_cart.setAdapter(adapter);
            low_baby_cart.setVisibility(View.GONE);
        } else {
            low_baby_cart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getChoiceData(int position, boolean isChoice) {

    }
}
