package com.naoh.Fruit.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by NaOH on 2016/4/18.
 */
public class Data {
    /**保存添加到购物车的数据*/
    public static int arrayList_cart_id=0;
    /**保存添加到购物车的数据*/
    public static ArrayList<HashMap<String, Object>> arrayList_cart=new ArrayList<HashMap<String,Object>>();
    /**保存购物车中选择的商品的总价数据*/
    public static float Allprice_cart=0;

    public static List<OrderBean> allOrderBeans;
    public static List<OrderBean> unHandledOrders = new LinkedList<OrderBean>();
    public static List<OrderBean> handledOrders = new LinkedList<OrderBean>();

    static {
        allOrderBeans = new ArrayList<OrderBean>();
        OrderBean orderBean = new OrderBean();
        orderBean.setOrderId("test1");
        orderBean.setPayed(1);
        orderBean.setHasSended(1);
        orderBean.setAddress("大连市");
        allOrderBeans.add(orderBean);

        OrderBean orderBean2 = new OrderBean();
        orderBean2.setOrderId("test2");
        orderBean2.setPayed(1);
        orderBean2.setHasSended(0);
        orderBean2.setAddress("北京市");
        allOrderBeans.add(orderBean2);

        for(OrderBean orderBean3: allOrderBeans)
        {
            if(orderBean3.getHasSended()==0)
            {
                unHandledOrders.add(orderBean3);
            }else{
                handledOrders.add(orderBean3);
            }
        }
    }
}
