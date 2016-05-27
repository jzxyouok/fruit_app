package com.naoh.Fruit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Data.SellerBean;

import java.util.List;
import java.util.Map;

public class Adapter_GridView extends BaseAdapter {
private Context context;
private int[] data;
	List<SellerBean> sellers = null;
	public Adapter_GridView(Context context, int[] data){
		this.context=context;
		this.data=data;
	}

	public Adapter_GridView(Context context, List<SellerBean> sellers){
		int [] datas = {R.drawable.menu_guide_1,R.mipmap.menu_guide_2};
		this.data = datas;
		this.context=context;
		this.sellers = sellers;
	}
	
	@Override
	public int getCount() {
		if(null!=sellers&& sellers.size()>0)
			return sellers.size();
		return data.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View currentView, ViewGroup arg2) {
		HolderView holderView=null;
		if (currentView==null) {
			holderView=new HolderView();
			currentView=LayoutInflater.from(context).inflate(R.layout.adapter_grid_home, null);
			holderView.iv_pic=(ImageView) currentView.findViewById(R.id.iv_adapter_grid_pic);
			currentView.setTag(holderView);
		}else {
			holderView=(HolderView) currentView.getTag();
		}

		if(null!=sellers&&sellers.size()>position)
			holderView.iv_pic.setImageResource(Integer.parseInt(sellers.get(position).getImage()));
		else
			holderView.iv_pic.setImageResource(data[position]);
		
		
		return currentView;
	}
	
	
	public class HolderView {
		
		private ImageView iv_pic;
		
	}

}
