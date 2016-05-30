package com.naoh.Fruit.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Adapter.Adapter_Comment_detail;
import com.naoh.Fruit.Data.Comment;
import com.naoh.Fruit.Data.ProductBean;
import com.naoh.Fruit.dao.CommentService;
import com.naoh.Fruit.dao.ProductService;
import com.naoh.Fruit.view.DetailPopWindow;
import com.naoh.Fruit.view.ScaleView.HackyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NaOH on 2016/4/15.
 */
public class DetailActivity extends FragmentActivity implements View.OnClickListener, DetailPopWindow.OnPopWindowClickListener {

    /**
     * 要显示的商品对象
     */
    ProductBean productBean;

    private HackyViewPager viewPager;
    private ArrayList<View> allListView;
    private int[] resId = {R.mipmap.detail_show_1, R.mipmap.detail_show_2, R.mipmap.detail_show_3, R.mipmap.detail_show_4, R.mipmap.detail_show_5, R.mipmap.detail_show_6};
     private ListView listView;
    private ImageView iv_baby_collection;
    /**
     * 弹出商品订单信息详情
     */
    private DetailPopWindow popWindow;
    /**
     * 用于设置背景暗淡
     */
    private LinearLayout all_choice_layout = null;
    /**
     * 判断是否点击的立即购买按钮
     */
    boolean isClickBuy = false;
    /**
     * 是否添加收藏
     */
    private static boolean isCollection = false;
    /**
     * ViewPager当前显示页的下标
     */
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 从Bundle中获取商品的ID
         */
        Bundle bundle = getIntent().getExtras();
        Long productId = bundle.getLong("productId");
        /**
         * 根据商品ID获取商品内容
         */
        ProductBean productBean = new ProductService(DetailActivity.this).getProductByProductId(productId);
        Log.i("ProductBean", productBean.toString());
        setContentView(R.layout.activity_detail);
        //得到保存的收藏信息
        getSaveCollection();
        initView();
        popWindow = new DetailPopWindow(this);
        popWindow.setOnPopWindowClickListener(this);
    }

    private void initView() {

        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.put_in)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.buy_now)).setOnClickListener(this);
        iv_baby_collection = (ImageView) findViewById(R.id.iv_baby_collection);
        iv_baby_collection.setOnClickListener(this);
        all_choice_layout = (LinearLayout) findViewById(R.id.all_choice_layout);
        /*这是评价*/
        listView = (ListView) findViewById(R.id.listView_Detail);
        listView.setFocusable(false);
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        //List<Comment> commentList = new CommentService(DetailActivity.this).findAllCommentByPid(productBean.getId());
        List<Comment> commentList = new ArrayList<Comment>();
        commentList.add(new Comment(1, 5, "味道不错，下次还买"));
        commentList.add(new Comment(2, 5, "很好吃，下次还买"));
        commentList.add(new Comment(3, 4, "味道一般，服务一般"));
        listView.setAdapter(new Adapter_Comment_detail(DetailActivity.this, commentList));
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            }
        });
        initViewPager();

        if (isCollection) {
            //如果已经收藏，则显示收藏后的效果
            iv_baby_collection.setImageResource(R.mipmap.second_2_collection);
        }
    }

    private void initViewPager() {

        if (allListView != null) {
            allListView.clear();
            allListView = null;
        }
        allListView = new ArrayList<View>();

        for (int i = 0; i < resId.length; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.pic_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
            imageView.setImageResource(resId[i]);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    //跳转到查看大图界面
//                    Intent intent = new Intent(DetailActivity.this, ShowBigPictrue.class);
//                    intent.putExtra("position", position);
//                    startActivity(intent);
                }
            });
            allListView.add(view);
        }

        viewPager = (HackyViewPager) findViewById(R.id.iv_baby);
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                position = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        viewPager.setAdapter(adapter);

    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return allListView.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (View) arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = allListView.get(position);
            container.addView(view);
            return view;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                //返回
                finish();
                break;
            case R.id.iv_baby_collection:
                //收藏
                if (isCollection) {
                    //提示是否取消收藏
                    cancelCollection();
                } else {
                    isCollection = true;
                    setSaveCollection();
                    //如果已经收藏，则显示收藏后的效果
                    iv_baby_collection.setImageResource(R.mipmap.second_2_collection);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.put_in:
                //添加购物车
                isClickBuy = false;
                setBackgroundBlack(all_choice_layout, 0);
                popWindow.showAsDropDown(view);
                break;
            case R.id.buy_now:
                //立即购买
                isClickBuy = true;
                setBackgroundBlack(all_choice_layout, 0);
                popWindow.showAsDropDown(view);
                break;
        }
    }

    @Override
    public void onClickOKPop() {
        setBackgroundBlack(all_choice_layout, 1);

        if (isClickBuy) {
            //如果之前是点击的立即购买，那么就跳转到立即购物界面
//            Intent intent = new Intent(DetailActivity.this, BuynowActivity.class);
//            startActivity(intent);
        } else {
            Toast.makeText(this, "添加到购物车成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickCancelPop() {
        setBackgroundBlack(all_choice_layout, 1);
    }

    /**
     * 控制背景变暗 0变暗 1变亮
     */
    public void setBackgroundBlack(View view, int what) {
        switch (what) {
            case 0:
                view.setVisibility(View.VISIBLE);
                break;
            case 1:
                view.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 保存是否添加收藏
     */
    private void setSaveCollection() {
        SharedPreferences sp = getSharedPreferences("SAVECOLLECTION", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isCollection", isCollection);
        editor.commit();
    }

    /**
     * 得到保存的是否添加收藏标记
     */
    private void getSaveCollection() {
        SharedPreferences sp = getSharedPreferences("SAVECOLLECTION", Context.MODE_PRIVATE);
        isCollection = sp.getBoolean("isCollection", false);

    }

    /**
     * 取消收藏
     */
    private void cancelCollection() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("是否取消收藏");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                isCollection = false;
                //如果取消收藏，则显示取消收藏后的效果
                iv_baby_collection.setImageResource(R.mipmap.second_2);
                setSaveCollection();
            }
        });
        dialog.setNegativeButton("取消", null);
        dialog.create().show();

    }
}
