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

    public int publishProduct(ProductBean productBean)
    {
        SQLiteDatabase db = null;
        try
        {
            db = dbHelper.getWritableDatabase();
            String sql = "insert into product(categoryId, sellerId, name, price, marketPrice, avail, desc, publishDate, image)"+
                    "values(?,?,?,?,?,?,?,?,?)";

            Object[] values = { productBean.getCategoryId(),
                    productBean.getSellerId(),
                    productBean.getName(),  productBean.getPrice(),
                    productBean.getMarketPrice(),
                    productBean.getAvail(),
                    productBean.getDesc(),
                    System.currentTimeMillis(),
                    productBean.getImage()
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


    public List<ProductBean> findLastNProducts(int categoryId, int n)
    {
        String sql = "";
        if(categoryId!=0)
            sql = SQL + " where categoryId=" + categoryId+" order by publishDate desc limit ?";
        else
            sql = SQL +"  order by publishDate desc limit ?";

        String[] values = { String.valueOf(n)};
        return  findLimitProducts(sql,values );
    }


    /**
     *
     * @param sellerId:卖家ID，如果小于1,获取所有卖家的
     * @param categoryId：目录ID，如果小于1，获取所有目录下的商品
     * @param start：起始位置
     * @param size：获取多少个
     * @return
     */
    public List<ProductBean> findProductsWithSellerAndCategoryId(int sellerId, int categoryId, int start, int size)
    {
        StringBuffer sb = new StringBuffer(SQL);
        boolean sellerPara = false;
        boolean categoryPara = false;
        if(sellerId>0||categoryId>0)
        {
            sb.append("Where ");
        }

        if(sellerId>0)
        {
            sb.append(" sellerId = ?");
            sellerPara = true;
        }

        if(categoryId > 0)
        {
            sb.append(" categoryId = ? " + categoryId);
            categoryPara = true;
        }

        sb.append(" limit ?, ?");

        if(sellerPara&&categoryPara)
        {
            String[] values = {String.valueOf(sellerId), String.valueOf(categoryId),
                    String.valueOf(start),String.valueOf(size)
            };
            return findLimitProducts(sb.toString(), values);
        }

        if(sellerPara)
        {
            String[] values = {String.valueOf(sellerId), String.valueOf(start),String.valueOf(size)
            };
            return findLimitProducts(sb.toString(), values);
        }

        if(categoryPara)
        {
            String[] values = {String.valueOf(categoryId), String.valueOf(start),String.valueOf(size)
            };
            return findLimitProducts(sb.toString(), values);
        }

        return  null;

    }

    public List<ProductBean> findLimitProducts(String sql, String[] values)
    {

        List<ProductBean> list = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try
        {
            db = dbHelper.getReadableDatabase();
            cursor = db.rawQuery(sql, values);

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
