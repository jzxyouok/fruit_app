package com.naoh.Fruit.ui.home;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Adapter.Adapter_Order;
import com.naoh.Fruit.Data.OrderBean;
import com.naoh.Fruit.view.xListview.XListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public class MyOrdersFragment extends Fragment implements View.OnTouchListener, XListView.IXListViewListener {

    //显示所有订单的列表
    private XListView listView;
    //整个顶部搜索控件，用于隐藏和显示底部整个控件
    private LinearLayout ll_search;

    @SuppressWarnings("unused")
    private EditText ed_search;

    private AnimationSet animationSet;
    /**
     * 第一次按下屏幕时的Y坐标
     */
    float fist_down_Y = 0;
    /**
     * 请求数据的页数
     */
    private int pageIndex = 0;

    /**
     * 存储网络返回的数据中的data字段
     */
    private List<OrderBean> orderBeanList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_allorders, null);
        initView(view);
        return view;
    }

    /**
     * 初始化视图
     * @param view
     */
    private void initView(View view) {

        if(orderBeanList==null) {
            orderBeanList = new LinkedList<OrderBean>();
            OrderBean orderBean= new OrderBean();
            orderBean.setOrderId("dafjsdkafiefdff");
            orderBean.setAddress("海淀区东升科技园A区4号楼");
            orderBeanList.add(orderBean);

            OrderBean orderBean2= new OrderBean();
            orderBean2.setOrderId("dafjsdkafiefdff");
            orderBean2.setAddress("海淀区东升科技园A区3号楼");
            orderBeanList.add(orderBean2);
        }

        ll_search = (LinearLayout)  view.findViewById(R.id.order_choice);
        ed_search = (EditText) view.findViewById(R.id.order_SearchOrder);

        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView = (XListView) view.findViewById(R.id.listOrderView);
        listView.setOnTouchListener(this);
        listView.setXListViewListener(this);
        // 设置可以进行下拉加载的功能
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(true);

        listView.setAdapter(new Adapter_Order(getActivity(), orderBeanList));
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    private class OrderTask extends AsyncTask<Void, Void, List<OrderBean>> {

        ProgressDialog dialog = null;

        @Override
        protected void onPreExecute() {
            if (dialog == null) {
                dialog = ProgressDialog.show(getActivity(), "", "正在加载...");
                dialog.show();
            }
        }

        @Override
        protected List<OrderBean> doInBackground(Void... params) {
//            if (orderBeanList == null) {
//                orderBeanList = new LinkedList<OrderBean>();
//            }
//            List<OrderBean> moreOrderBean = new OrderService(getActivity()).findMoreOrzzderBean(sellerId, pageIndex, 10);
            //orderBeanList.addAll(moreOrderBean);


            return null;
        }

        @Override
        protected void onPostExecute(List<OrderBean> result) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }


            // 停止刷新和加载
            onLoad();
        }

    }

    /**
     * 停止加载和刷新
     */
    private void onLoad() {
        listView.stopRefresh();
        // 停止加载更多
        listView.stopLoadMore();

        // 设置最后一次刷新时间
        listView.setRefreshTime(getCurrentTime(System.currentTimeMillis()));
    }

    /**
     * 简单的时间格式
     */
    public static SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");

    public static String getCurrentTime(long time) {
        if (0 == time) {
            return "";
        }

        return mDateFormat.format(new Date(time));
    }
}
