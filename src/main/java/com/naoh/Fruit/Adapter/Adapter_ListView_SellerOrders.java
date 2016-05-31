package com.naoh.Fruit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Data.OrderBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public class Adapter_ListView_SellerOrders extends BaseAdapter {

    List<OrderBean> orderBeanList;
    Context context;

    private onCheckedChanged listener;

    public static int[] imageIds = {R.mipmap.putao_image, R.mipmap.ningmeng_image, R.mipmap.caomei_image, R.mipmap.huolongguo_image,
            R.mipmap.yingtao, R.mipmap.chengzi_image, R.mipmap.pingguo_image,
            R.mipmap.qiyiguo_image, R.mipmap.taozi_image, R.mipmap.li_image
    };

    public static String[] pnames = {
            "葡萄","柠檬","草莓","火龙果","樱桃", "橙子", "苹果" , "奇异果" , "桃子","梨"

    };


    public Adapter_ListView_SellerOrders(Context context, List<OrderBean> orderBeanList)
    {
        this.orderBeanList = orderBeanList;
        this.context = context;
    }



    @Override
    public int getCount() {
        return null==orderBeanList||orderBeanList.size()==0?10:orderBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View currentView, ViewGroup parent) {
        HolderView holderView = null;
        if (currentView == null) {
            holderView = new HolderView();
            currentView = LayoutInflater.from(context).inflate(R.layout.adpater_all_seller_orders, null);
            holderView.iv_adapter_list_pic = (ImageView) currentView.findViewById(R.id.iv_adapter_list_pic);
            holderView.tv_order_pname = (TextView) currentView.findViewById(R.id.tv_order_pname);
            holderView.tv_total_price = (TextView) currentView.findViewById(R.id.tv_total_price);
            holderView.tv_order_num = (TextView) currentView.findViewById(R.id.tv_order_num);
            holderView.cb_choice = (CheckBox) currentView.findViewById(R.id.cb_choice);
            currentView.setTag(holderView);
        } else {
            holderView = (HolderView) currentView.getTag();
        }
        if (null!=orderBeanList&&orderBeanList.size() != 0) {

            holderView.iv_adapter_list_pic.setImageResource(imageIds[position%10]);
            holderView.tv_order_pname.setText(pnames[position%10]);
            holderView.tv_order_num.setText("10个");
            holderView.tv_total_price.setText("￥32元");
            holderView.cb_choice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton arg0, boolean choice) {
                    listener.getChoiceData(position, choice);
                }
            });

        }
        return currentView;
    }

    public class HolderView {
        private ImageView iv_adapter_list_pic;
        private TextView tv_order_pname;
        private TextView tv_total_price;
        private TextView tv_order_num;
        private CheckBox cb_choice;

    }
    public interface onCheckedChanged {

        void getChoiceData(int position, boolean isChoice);
    }

    public void setOnCheckedChanged(onCheckedChanged listener) {
        this.listener = listener;
    }
}
