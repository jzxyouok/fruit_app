package com.naoh.Fruit.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.widget.EditText;

import com.naoh.Fruit.Data.ProductBean;
import com.naoh.Fruit.ui.home.DetailActivity;

/**
 * Created by Administrator on 2016/5/30.
 */
public class CommentService extends AbstractService {
    Context context;
    public CommentService(Context context) {
        this.context = context;
    }

    public int addComment(ProductBean productBean, String comment_content) {
        SQLiteDatabase db = null;
        try
        {
            db = dbHelper.getWritableDatabase();
            String sql = "insert into comment(productId, userid,  publishDate, content, rank) values(?,?, ?,?,?)";

            Object[] values = { productBean.getId(),
                    1,
                    System.currentTimeMillis(),  comment_content,5
            };
            db.execSQL(sql, values);

        } catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        } finally
        {
            if (db != null)
                db.close();
        }

        return  1;
    }
}
