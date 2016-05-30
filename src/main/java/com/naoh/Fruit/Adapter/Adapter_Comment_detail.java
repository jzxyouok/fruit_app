package com.naoh.Fruit.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Data.Comment;
import com.naoh.Fruit.ui.home.DetailActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class Adapter_Comment_detail extends BaseAdapter {
    List<Comment> commentList;
    Context context;
    public Adapter_Comment_detail(Context context,List<Comment> commentList ) {
        this.commentList = commentList;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(null!=commentList)
            return commentList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(null!=commentList)
            return commentList.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_user_comment, null);
            holderView.iv_pic = (ImageView) convertView.findViewById(R.id.iv_adapter_comment_list_pic);
            holderView.iv_rank = (ImageView) convertView.findViewById(R.id.iv_adapter_comment_rank);
            holderView.content = (TextView) convertView.findViewById(R.id.tv_adapter_comment_content);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        if (commentList.size() != 0) {
            holderView.content.setText(commentList.get(position).getContent());
        }
        return convertView;
    }

    public class HolderView {
        private ImageView iv_pic;
        private ImageView iv_rank;
        private TextView content;
    }
}
