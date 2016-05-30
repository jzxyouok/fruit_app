package com.naoh.Fruit.ui.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Adapter.AdminPublishedProductAdapter;
import com.naoh.Fruit.Data.ProductBean;
import com.naoh.Fruit.dao.ProductService;
import com.naoh.Fruit.ui.home.DetailActivity;
import com.naoh.Fruit.ui.photo.PublishActivity;
import com.naoh.Fruit.view.xListview.XListView;

import java.util.List;

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

        productListView.stopRefresh();
        productListView.stopRefresh();

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

        final List<ProductBean> productBeanList = new ProductService(getActivity()).findProductsWithSellerAndCategoryId(1, 0, 0, 10);

        AdminPublishedProductAdapter productAdapter = new AdminPublishedProductAdapter(getActivity(), productBeanList);
        productListView.setAdapter(productAdapter);
        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到详情界面
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("productId", id);
                intent.putExtras(bundle);
                Log.i("Click productId", String.valueOf(id));
                startActivity(intent);
            }
        });

        productListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                ImageButton imageButton = (ImageButton) view.findViewById(R.id.iv_adapter_product_remove);
                imageButton.setVisibility(View.VISIBLE);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(getActivity()).setTitle("确认删除吗？")
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // 点击“确认”后的操作
                                        productBeanList.remove(position);

                                    }
                                })
                                .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                }).show();
                    }
                });
                return true;
            }
        });
    }
}
