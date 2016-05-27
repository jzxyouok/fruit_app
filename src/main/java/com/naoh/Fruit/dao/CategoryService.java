package com.naoh.Fruit.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.naoh.Fruit.Data.Category;
import com.naoh.Fruit.dao.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
public class CategoryService extends AbstractService {

    public CategoryService(Context context)
    {
        this.dbHelper = new DBHelper(context);
    }

    /**
     * 从数据库中查取所有Category
     * @return
     */
    public List<Category> findAll()
    {
        List<Category> list = new ArrayList<Category>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try
        {
            db = dbHelper.getReadableDatabase();
            String sql = "select id, name, image from category";
            cursor = db.rawQuery(sql, null);

            while (cursor.moveToNext())
            {
                // 依次取得每行数据
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int imageId = cursor.getInt(2);

                // 把每行数据依次添加到一个RecordJavaBean中，再把这个javabean加入list中
                Category record = new Category();

                record.setId(id);
                record.setName(name);
                record.setImage(imageId);
                System.out.println( "get category :" + id +"," + name +"," + imageId );

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
