package com.naoh.Fruit.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.naoh.Fruit.Data.ProductBean;
import com.naoh.Fruit.dao.util.DBHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
public class ProductService extends  AbstractService {
    public static final String  SQL = "select id, categoryId, sellerId, name, place, price, marketPrice, avail, desc, publishDate, image from product ";

    public ProductService(Context context)
    {
        this.dbHelper = new DBHelper(context);
    }


    public List<ProductBean> findLimitProducts(int start, int size)
    {
        String sql = SQL + "limit ?, ?";
        List<ProductBean> list = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try
        {
            db = dbHelper.getReadableDatabase();
            cursor = db.rawQuery(sql, null);

            list = getProductListByCursor(cursor);

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (null != db)
            {
                db.close();
            }

            if (null != cursor)
            {
                cursor.close();
            }
        }
        return list;
    }

    /**
     * 公用的，根据cursor封装成一个ProductBean
     * @param cursor
     * @return
     */
    private List<ProductBean> getProductListByCursor(Cursor cursor)
    {
        if(cursor==null)
            return null;
        List<ProductBean> list = new LinkedList<ProductBean>();
        while (cursor.moveToNext())
        {
            // 依次取得每行数据
            int id = cursor.getInt(0);
            int categoryId = cursor.getInt(1);
            int sellerId = cursor.getInt(2);
            String name = cursor.getString(3);
            String place = cursor.getString(4);
            double price = cursor.getDouble(5);
            double marketPrice = cursor.getDouble(6);
            int avail = cursor.getInt(7);
            String desc = cursor.getString(8);
            long publishDate = cursor.getLong(9);
            String image = cursor.getString(10);


            // 把每行数据依次添加到一个ProductBean中，再把这个javabean加入list中
            ProductBean record = new ProductBean();

            record.setId(id);
            record.setCategoryId(categoryId);
            record.setSellerId(sellerId);
            record.setName(name);
            record.setPlace(place);
            record.setPrice(price);
            record.setMarketPrice(marketPrice);
            record.setAvail(avail);
            record.setDesc(desc);
            record.setImage(image);
            System.out.println( record);

            list.add(record);
        }

        return list;
    }
}
