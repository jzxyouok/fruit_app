package com.naoh.Fruit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Data.Category;

import java.util.List;

/**
 * Created by Administrator on 2016/5/28.
 */
public class CategoryGridAdapter extends BaseAdapter {
    private Context context;
    List<Category> categoryList;
    public CategoryGridAdapter(Context context, List<Category> categoryList)
    {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return  categoryList==null?0:categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categoryList.get(position).getId();
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {
        HolderView holderView=null;
        if (currentView==null) {
            holderView=new HolderView();
            currentView= LayoutInflater.from(context).inflate(R.layout.adapter_grid_home, null);
            holderView.iv_pic=(ImageView) currentView.findViewById(R.id.iv_adapter_grid_pic);
            currentView.setTag(holderView);
        }else {
            holderView=(HolderView) currentView.getTag();
        }

        if(null!=categoryList&&categoryList.size()>position)
            holderView.iv_pic.setImageResource(categoryList.get(position).getImage());
        else
            holderView.iv_pic.setImageResource(R.mipmap.qita);


        return currentView;
    }

    public class HolderView {

        private ImageView iv_pic;

    }
}
