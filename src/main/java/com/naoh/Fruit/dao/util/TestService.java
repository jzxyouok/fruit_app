package com.naoh.Fruit.dao.util;

import android.content.Context;
import android.database.Cursor;

import com.naoh.Fruit.dao.AbstractService;

/**
 * Created by Administrator on 2016/5/26.
 */
public class TestService extends AbstractService {


    public TestService(Context context)
    {
        this.dbHelper = new DBHelper(context);
    }

    /**
     * 获取Category的数量
     * @return
     */
    public int getCategoryCount()
    {
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("select count(*)from category",null);

        //游标移到第一条记录准备获取数据
        cursor.moveToFirst();
        // 获取数据中的Int类型数据
        int count = cursor.getInt(0);

        return count;
    }

}
