package com.naoh.Fruit.dao.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/26.
 */
public class DBConstants {
    // 1.
    public static final String CREATE_TABLE_USER="create table user (" +
            "id integer primary key autoincrement," +
            "name varchar(32)," +
            "telephone varchar(16)," +
            "address varchar(128)," +
            "password varchar(64)," +
            "image varchar(128)"+
            ")";
    // 2.
    public  static final String CREATE_TABLE_SELLER="create table seller (" +
            "id integer primary key autoincrement," +
            "name varchar(32)," +
            "telephone varchar(16)," +
            "address varchar(128)," +
            "password varchar(64)," +
            "image varchar(128)"+
            ")";

    // 3.
    public static final String CREATE_TABLE_PRODUCT="create table product (" +
            "id integer primary key autoincrement," +
            "categoryId integer," +
            "sellerId integer," +
            "name varchar(64)," +
            "place varchar(128)," +
            "price REAL," +
            "avail integer," +
            "desc text," +
            "publishDate long" +
            ")";

    // 4.
    public static final String CREATE_TABLE_PRODUCT_IMAGE="create table productimage(" +
            "id integer primary key autoincrement," +
            "productId integer," +
            "path varchar(128)" +
            ")";

    // 5.
    public static final String CREATE_TABLE_PRODUCT_REDUCTION_RECORD= "create table productpricereductionrecord ("+
            "id integer primary key autoincrement,"+
            "productId integer,"+
            "originPrice real,"+
            "currentPrice real,"+
            "date long"+
            ")";

    // 6.
    public static final String CREATE_TABLE_PRODUCT_WATCH_RECORD= "create table productwatchrecord ("+
            "id integer primary key autoincrement,"+
            "userID integer,"+
            "productId real"+
            ")";

    // 7.
    public static final String CREATE_TABLE_TRADING_RECORD= "create table tradingrecord (" +
            "id integer primary key autoincrement," +
            "userID integer," +
            "sellerId integer," +
            "productId real," +
            "tradeCount integer," +
            "tradeDate long," +
            "money real," +
            "orderId varchar(128)" +
            ")";

    // 8.
    public static final String CREATE_TABLE_CATEGORY = "create table category ("+
            "id integer primary key autoincrement,"+
            "name varchar(64)"+
            ")";


    public static final Map<String, String> sqlCreateMap = new HashMap<String, String>();
    public static final List<String> insertSqls = new LinkedList<String>();

    static {
        sqlCreateMap.put("CREATE_TABLE_USER", CREATE_TABLE_USER);
        sqlCreateMap.put("CREATE_TABLE_SELLER", CREATE_TABLE_SELLER);
        sqlCreateMap.put("CREATE_TABLE_PRODUCT", CREATE_TABLE_PRODUCT);
        sqlCreateMap.put("CREATE_TABLE_PRODUCT_IMAGE", CREATE_TABLE_PRODUCT_IMAGE);
        sqlCreateMap.put("CREATE_TABLE_PRODUCT_REDUCTION_RECORD", CREATE_TABLE_PRODUCT_REDUCTION_RECORD);
        sqlCreateMap.put("CREATE_TABLE_PRODUCT_WATCH_RECORD", CREATE_TABLE_PRODUCT_WATCH_RECORD);
        sqlCreateMap.put("CREATE_TABLE_TRADING_RECORD", CREATE_TABLE_TRADING_RECORD);
        sqlCreateMap.put("CREATE_TABLE_CATEGORY", CREATE_TABLE_CATEGORY);

        insertSqls.add("insert into category(name) values('苹果')");
        insertSqls.add("insert into category(name) values('香蕉')");
        insertSqls.add("insert into category(name) values('梨')");
        insertSqls.add("insert into category(name) values('橙子')");
        insertSqls.add("insert into category(name) values('火龙果')");
        insertSqls.add("insert into category(name) values('菠萝')");
        insertSqls.add("insert into category(name) values('桃子')");
        insertSqls.add("insert into category(name) values('西瓜')");
        insertSqls.add("insert into category(name) values('柠檬')");
        insertSqls.add("insert into category(name) values('葡萄')");
        insertSqls.add("insert into category(name) values('草莓')");
        insertSqls.add("insert into category(name) values('荔枝')");
        insertSqls.add("insert into category(name) values('猕猴桃')");
        insertSqls.add("insert into category(name) values('木瓜')");
        insertSqls.add("insert into category(name) values('芒果')");
        insertSqls.add("insert into category(name) values('哈密瓜')");
        insertSqls.add("insert into category(name) values('柚子')");
        insertSqls.add("insert into category(name) values('樱桃')");
        insertSqls.add("insert into category(name) values('其他')");

        insertSqls.add("insert into seller(name, telephone, address, password, image) values('admin', '18808884762', '大连', 'admin', '')");
        insertSqls.add("insert into seller(name, telephone, address, password, image) values('seller1', '18808884761', '北京', 'seller1', '')");
        insertSqls.add("insert into seller(name, telephone, address, password, image) values('admin1', '18808884762', '大连', 'admin', '')");
        insertSqls.add("insert into seller(name, telephone, address, password, image) values('seller2', '18808884761', '北京', 'seller1', '')");
        insertSqls.add("insert into seller(name, telephone, address, password, image) values('admin2', '18808884762', '大连', 'admin', '')");
        insertSqls.add("insert into seller(name, telephone, address, password, image) values('seller3', '18808884761', '北京', 'seller1', '')");

    }



}
