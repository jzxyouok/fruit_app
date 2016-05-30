package com.naoh.Fruit.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Adapter.Adapter_GridView;
import com.naoh.Fruit.Adapter.CategoryGridAdapter;
import com.naoh.Fruit.Data.ProductBean;
import com.naoh.Fruit.Data.SellerBean;
import com.naoh.Fruit.ab.view.AbOnItemClickListener;
import com.naoh.Fruit.ab.view.AbSlidingPlayView;
import com.naoh.Fruit.dao.CategoryService;
import com.naoh.Fruit.dao.ProductService;
import com.naoh.Fruit.dao.SellerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NaOH on 2016/4/15.
 */
public class HomeFragment extends Fragment {

    /**
     * category网格
     */
    private GridView categoryGrid;
    private CategoryGridAdapter categoryGridAdapter;

    //分类的九宫格
    private GridView gridView_classify;
    private Adapter_GridView adapter_GridView_classify;
    //首页轮播
    private AbSlidingPlayView viewPager;
    // 分类九宫格的资源文件
//    private int[] pic_path_classify = {R.mipmap.menu_guide_1, R., R.mipmap.menu_guide_3, R.mipmap.menu_guide_4, R.mipmap.menu_guide_5, R.mipmap.menu_guide_6, R.mipmap.menu_guide_7, R.mipmap.menu_guide_8};
    private int[] pic_path_classify = {R.drawable.menu_guide_1,R.mipmap.menu_guide_2};

    private List<SellerBean> sellers = new ArrayList<SellerBean>();

    /**
     * 存储首页轮播的界面
     */
    private ArrayList<View> allListView;
    /**
     * 首页轮播的界面的资源
     */
//    private int[] resId = {R.mipmap.menu_viewpager_1, R.mipmap.menu_viewpager_2, R.mipmap.menu_viewpager_3, R.mipmap.menu_viewpager_4, R.mipmap.menu_viewpager_5, R.mipmap.menu_viewpager_6};
    private List<ProductBean> last5Product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        initView(view);
        return view;
    }

    /**
     * 获取所有卖家信息
     */
    private void initSellers()
    {
        sellers = new SellerService(getActivity()).findAll();
        int i=0;
        for(SellerBean sellerBean: sellers)
        {
            sellerBean.setImage(String.valueOf(i%2==0?R.drawable.menu_guide_1:R.mipmap.menu_guide_2));
            i++;
        }

    }

    /**
     * 获取所有分类
     */

    /**
     * 获取最新的5个产品
     */
    private void initLast5Product()
    {
        last5Product = new ProductService(getActivity()).findLastNProducts(0, 5);
    }


    private void initView(View view) {
        initSellers();
        initLast5Product();


//        gridView_classify = (GridView) view.findViewById(R.id.my_gridview);
//        gridView_classify.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        //adapter_GridView_classify = new Adapter_GridView(getActivity(), pic_path_classify);
//        adapter_GridView_classify = new Adapter_GridView(getActivity(), sellers);
//        gridView_classify.setAdapter(adapter_GridView_classify);


        categoryGrid = (GridView)view.findViewById(R.id.categoryGird);
        categoryGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));
        categoryGridAdapter = new CategoryGridAdapter(getActivity(), new CategoryService(getActivity()).findAll());
        categoryGrid.setAdapter(categoryGridAdapter);

        viewPager = (AbSlidingPlayView) view.findViewById(R.id.viewPager_menu);
        //设置播放方式为顺序播放
        viewPager.setPlayType(1);
        //设置播放间隔时间
        viewPager.setSleepTime(3000);

//        gridView_classify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                //跳转到宝贝搜索界面
//                Intent intent = new Intent(getActivity(), WareActivity.class);
//                startActivity(intent);
//            }
//        });

        categoryGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //跳转到宝贝搜索界面
                Intent intent = new Intent(getActivity(), WareActivity.class);
                startActivity(intent);
            }
        });

        initViewPager();
    }

    private void initViewPager() {

        if (allListView != null) {
            allListView.clear();
            allListView = null;
        }
        allListView = new ArrayList<View>();


        if(last5Product!=null)
        {
            for(ProductBean productBean: last5Product)
            {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.pic_item, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
                try{
                    imageView.setImageResource(Integer.parseInt(productBean.getImage()));
                }catch (Exception ex)
                {
                    Bitmap bit = BitmapFactory.decodeFile(productBean.getImage());
                    imageView.setImageBitmap(bit);
                }
                allListView.add(view);

            }
        }

//        for (int i = 0; i < resId.length; i++) {
//            //导入ViewPager的布局
//            View view = LayoutInflater.from(getActivity()).inflate(R.layout.pic_item, null);
//            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
//            imageView.setImageResource(resId[i]);
//            allListView.add(view);
//        }


        viewPager.addViews(allListView);
        //开始轮播
        viewPager.startPlay();
        viewPager.setOnItemClickListener(new AbOnItemClickListener() {
            @Override
            public void onClick(int position) {
                //跳转到详情界面
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("productId", last5Product.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
