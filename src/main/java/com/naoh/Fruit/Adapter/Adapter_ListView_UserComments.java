package com.naoh.Fruit.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2016/5/31.
 */
public class Adapter_ListView_UserComments extends BaseAdapter {

    private onCheckedChanged listener;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public interface onCheckedChanged {

        void getChoiceData(int position, boolean isChoice);
    }

    public void setOnCheckedChanged(onCheckedChanged listener) {
        this.listener = listener;
    }
}
