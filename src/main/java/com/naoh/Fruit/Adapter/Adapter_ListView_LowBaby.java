package com.naoh.Fruit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Data.ProductBean;
import com.naoh.Fruit.Data.ProductPriceReductionRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/31.
 */
public class Adapter_ListView_LowBaby extends BaseAdapter {
    List<ProductPriceReductionRecord> lowBabies;
    Context context;
    private Adapter_ListView_LowBaby.onCheckedChanged listener;

    public Adapter_ListView_LowBaby(Context context, List<ProductPriceReductionRecord> lowBabies)
    {
        this.context = context;
        this.lowBabies = lowBabies;
    }
    @Override
    public int getCount() {
        return lowBabies==null?0:lowBabies.size();
    }

    @Override
    public Object getItem(int position) {
        return lowBabies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View currentView, ViewGroup arg2) {
        HolderView holderView = null;
        if (currentView == null) {
            holderView = new HolderView();
            currentView = LayoutInflater.from(context).inflate(R.layout.adapter_lowbaby_listview, null);
            holderView.tv_origin_price = (TextView) currentView.findViewById(R.id.tv_origin_price);
            holderView.tv_current_price = (TextView) currentView.findViewById(R.id.tv_current_price);
            holderView.tv_product_name = (TextView) currentView.findViewById(R.id.tv_lowbaby_name);
            currentView.setTag(holderView);
        } else {
            holderView = (HolderView) currentView.getTag();
        }
        if (lowBabies.size() != 0) {
            Log.i("LowBaby", lowBabies.get(position).toString());
            holderView.tv_origin_price.setText("原价:￥" + lowBabies.get(position).getOriginPrice());
            holderView.tv_current_price.setText("现价:￥" + lowBabies.get(position).getCurrentPrice());
            holderView.tv_product_name.setText( lowBabies.get(position).getProductBean().getName());

        }
        return currentView;
    }

    public class HolderView {

        private TextView tv_product_name;
        private TextView tv_origin_price;
        private TextView tv_current_price;

    }

    public interface onCheckedChanged {

        void getChoiceData(int position, boolean isChoice);
    }

    public void setOnCheckedChanged(onCheckedChanged listener) {
        this.listener = listener;
    }
}
