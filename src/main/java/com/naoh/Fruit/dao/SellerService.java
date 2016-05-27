package com.naoh.Fruit.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.naoh.Fruit.Data.SellerBean;
import com.naoh.Fruit.dao.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
public class SellerService extends AbstractService {

    public SellerService(Context context)
    {
        this.dbHelper = new DBHelper(context);
    }

    public List<SellerBean> findAll()
    {
        List<SellerBean> list = new ArrayList<SellerBean>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try
        {
            db = dbHelper.getReadableDatabase();
            String sql = "select * from seller";
            cursor = db.rawQuery(sql, null);

            while (cursor.moveToNext())
            {
                // 依次取得每行数据
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String telephone = cursor.getString(2);
                String address = cursor.getString(3);
                String image = cursor.getString(5);

                // 把每行数据依次添加到一个RecordJavaBean中，再把这个javabean加入list中
                SellerBean record = new SellerBean();

                record.setId(id);
                record.setName(name);
                record.setAddress(address);
                record.setTelephone(telephone);
                System.out.println( "get sellers :" + id+"," + name +"," + telephone  +"," + address+"," + image);

                list.add(record);
            }

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
}
