package com.naoh.Fruit.dao;

import android.content.Context;

import com.naoh.Fruit.dao.util.DBHelper;

/**
 * Created by Administrator on 2016/5/29.
 */
public class OrderService extends AbstractService {

    private static final String SQL = "select orderId, userId, sellerId, payed, hasSended, address from orders ";

    public OrderService(Context context)
    {
        this.dbHelper = new DBHelper(context);
    }

}
