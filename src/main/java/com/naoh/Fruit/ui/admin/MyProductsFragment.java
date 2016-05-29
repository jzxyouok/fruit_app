package com.naoh.Fruit.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.ui.photo.PublishActivity;
import com.naoh.Fruit.view.xListview.XListView;

/**
 * 显示商家发布的商品
 */
public class MyProductsFragment extends Fragment {
    int sellerId;
    XListView productListView;
    TextView publishProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_adminproducts, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        productListView = (XListView)view.findViewById(R.id.listMyProducts);
        publishProduct = (TextView) view.findViewById(R.id.activity_publish_prudct);

        publishProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PublishActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("sellerId", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
