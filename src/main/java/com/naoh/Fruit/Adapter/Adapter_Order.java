package com.naoh.Fruit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Data.OrderBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/29.
 */
public class Adapter_Order  extends BaseAdapter {
    private Context context;
    List<OrderBean> orderBeanList;

    public Adapter_Order(Context context) {
        this.context = context;
    }

    public Adapter_Order(Context context, List<OrderBean> orderBeanList) {
        this.context = context;
        this.orderBeanList = orderBeanList;
    }

    @Override
    public int getCount() {
        if(orderBeanList!=null)
            return orderBeanList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(orderBeanList!=null)
            orderBeanList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        if (convertView == null) {
            holderView = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_listview_ware, null);
            holderView.iv_pic = (ImageView) convertView.findViewById(R.id.iv_adapter_list_pic);
            holderView.tv_desc = (TextView) convertView.findViewById(R.id.name);
            holderView.tv_toal_price = (TextView) convertView.findViewById(R.id.price);
            holderView.tv_sale_num = (TextView) convertView.findViewById(R.id.sale_num);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        if (orderBeanList.size() != 0) {
            buildHolderView(holderView, orderBeanList.get(position));
        }
        return convertView;
    }

    public class HolderView {
        private ImageView iv_pic;
        private TextView tv_sale_num;
        private TextView tv_desc;
        private TextView tv_toal_price;
    }

    private void buildHolderView(HolderView holderView, OrderBean orderBean)
    {

        holderView.iv_pic.setImageResource(R.mipmap.hainan_banana);
        holderView.tv_desc.setText("海南香蕉两斤");
        holderView.tv_toal_price.setText("￥20元");
        holderView.tv_sale_num.setText("购买2斤" + orderBean.getAddress());

    }
}
