package com.naoh.Fruit.dao.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Set;

/**
 * SQLite帮助类
 * Created by Administrator on 2016/5/26.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="fruit.db";
    public static final int VERSION = 1;

    public DBHelper(Context context)
    {
        this(context,DB_NAME,null,VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        /**
         * 执行SQL语句，初始化数据库，创建响应的数据库表
         */
        Set<String> keyset = DBConstants.sqlCreateMap.keySet();
        for(String key: keyset)
        {
            db.execSQL(DBConstants.sqlCreateMap.get(key));
        }

        // 插入数据
        for(String insertSQL: DBConstants.insertSqls)
        {
            db.execSQL(insertSQL);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    /**
     * 获取数据库
     * @return
     */
    public SQLiteDatabase getSQLiteDatabase() {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
        } catch (SQLiteException e) {
            db = this.getReadableDatabase();
        }
        return db;
    }
}
