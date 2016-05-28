package com.naoh.Fruit.dao.util;

import com.example.administrator.myapplication.R;

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
            "marketPrice REAL," +
            "avail integer," +
            "desc text," +
            "publishDate long," +
            "image varchar(128)"+
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
            "name varchar(64),"+
            "image integer"+
            ")";

    // 9.
    public static final String CREATE_TABLE_COMMENT = "create table comment(" +
            "id integer primary key autoincrement," +
            "productId integer," +
            "userid integer," +
            "username name," +
            "publishDate long," +
            "content varchar(256)," +
            "rank integer" +
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
        sqlCreateMap.put("CREATE_TABLE_COMMENT", CREATE_TABLE_COMMENT);

        insertSqls.add("insert into category(name, image) values('苹果',"+R.mipmap.pingguo+")");
        insertSqls.add("insert into category(name, image) values('香蕉',"+R.mipmap.xiangjiao+")");
        insertSqls.add("insert into category(name, image) values('梨',"+R.mipmap.li+")");
        insertSqls.add("insert into category(name, image) values('橙子',"+R.mipmap.chengzi+")");
        insertSqls.add("insert into category(name, image) values('火龙果',"+R.mipmap.huolongguo+")");
        insertSqls.add("insert into category(name, image) values('菠萝',"+R.mipmap.boluo+")");
        insertSqls.add("insert into category(name, image) values('桃子',"+R.mipmap.taozi+")");
        insertSqls.add("insert into category(name, image) values('西瓜',"+R.mipmap.xigua+")");
        insertSqls.add("insert into category(name, image) values('柠檬',"+R.mipmap.ningmeng+")");
        insertSqls.add("insert into category(name, image) values('葡萄',"+R.mipmap.putao+")");
        insertSqls.add("insert into category(name, image) values('草莓',"+R.mipmap.caomei+")");
        insertSqls.add("insert into category(name, image) values('荔枝',"+R.mipmap.lizhi+")");
        insertSqls.add("insert into category(name, image) values('猕猴桃',"+R.mipmap.mihoutao+")");
        insertSqls.add("insert into category(name, image) values('木瓜',"+R.mipmap.mugua+")");
        insertSqls.add("insert into category(name, image) values('芒果',"+R.mipmap.mangguo+")");
        insertSqls.add("insert into category(name, image) values('哈密瓜',"+R.mipmap.hamigua+")");
        insertSqls.add("insert into category(name, image) values('柚子',"+R.mipmap.youzi+")");
        insertSqls.add("insert into category(name, image) values('樱桃',"+R.mipmap.yingtao+")");
        insertSqls.add("insert into category(name, image) values('其他',"+R.mipmap.qita+")");

        insertSqls.add("insert into seller(name, telephone, address, password, image) values('admin', '18808884762', '大连', 'admin', '')");
        insertSqls.add("insert into seller(name, telephone, address, password, image) values('seller1', '18808884761', '北京', 'seller1', '')");
        insertSqls.add("insert into seller(name, telephone, address, password, image) values('admin1', '18808884762', '大连', 'admin', '')");
        insertSqls.add("insert into seller(name, telephone, address, password, image) values('seller2', '18808884761', '北京', 'seller1', '')");
        insertSqls.add("insert into seller(name, telephone, address, password, image) values('admin2', '18808884762', '大连', 'admin', '')");
        insertSqls.add("insert into seller(name, telephone, address, password, image) values('seller3', '18808884761', '北京', 'seller1', '')");


        insertSqls.add("insert into user (name, telephone,address, password, image) values( 'alvin', '10086', '大连', '123456', '')");
        insertSqls.add("insert into user (name, telephone,address, password, image) values( 'user1', '10010', '大连', '123456', '')");
        insertSqls.add("insert into user (name, telephone,address, password, image) values( 'user2', '10086', '大连', '123456', '')");

        insertSqls.add("insert into product (categoryId, sellerId, name, place, price, marketPrice, avail, desc, publishDate, image) values(1, 1, '山东红富士', '山东烟台', 8.0, 13.0, 50, '正宗山东烟台红富士', "+System.currentTimeMillis()+", '"+ R.mipmap.yantaiapple+"')");
        insertSqls.add("insert into product (categoryId, sellerId, name, place, price, marketPrice, avail, desc, publishDate, image) values(2, 2, '海南香蕉', '海南', 5.0, 7.0, 30, '正宗海南香蕉', "+System.currentTimeMillis()+", '"+ R.mipmap.hainan_banana+"')");



    }



}
