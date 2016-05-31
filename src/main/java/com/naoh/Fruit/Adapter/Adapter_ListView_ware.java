package com.naoh.Fruit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.myapplication.R;
import com.naoh.Fruit.http.CU_VolleyTool;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by NaOH on 2016/4/18.
 */
public class Adapter_ListView_ware extends BaseAdapter {
    public static int[] imageIds = {R.mipmap.putao_image, R.mipmap.ningmeng_image, R.mipmap.caomei_image, R.mipmap.huolongguo_image,
        R.mipmap.yingtao, R.mipmap.chengzi_image, R.mipmap.pingguo_image,
            R.mipmap.qiyiguo_image, R.mipmap.taozi_image, R.mipmap.li_image
    };

    private Context context;
    @SuppressWarnings("unused")
    private int[] data;
    private ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();

    public Adapter_ListView_ware(Context context, ArrayList<HashMap<String, Object>> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public Adapter_ListView_ware(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return (arrayList != null && arrayList.size() == 0) ? 30 : arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
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
            holderView.tv_name = (TextView) convertView.findViewById(R.id.name);
            holderView.tv_price = (TextView) convertView.findViewById(R.id.price);
            holderView.tv_sale_num = (TextView) convertView.findViewById(R.id.sale_num);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        if (arrayList.size() != 0) {

            ImageLoader.ImageListener listener = ImageLoader.getImageListener(holderView.iv_pic, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
            CU_VolleyTool.getInstance(context).getmImageLoader().get("http://192.168.0.111:3000/taoBao/img/" + arrayList.get(position).get("pic"), listener);

            holderView.tv_name.setText(arrayList.get(position).get("name").toString());
            holderView.tv_price.setText("￥" + arrayList.get(position).get("price").toString() + "元");
            holderView.tv_sale_num.setText("月销量:" + arrayList.get(position).get("sale_num").toString() + "件     " + arrayList.get(position).get("address").toString());
        }


        holderView.iv_pic.setImageResource(imageIds[position%10]);

        return convertView;
    }

    public class HolderView {
        private ImageView iv_pic;
        private TextView tv_sale_num;
        private TextView tv_name;
        private TextView tv_price;
    }

}
