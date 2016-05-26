package com.naoh.Fruit.Data;

import java.util.Date;
import java.util.List;

/**
 * 商品信息
 * Created by Administrator on 2016/5/26.
 */
public class ProductBean {
    // 商品ID
    int id;
    // 商品分类ID
    int categoryId;
    // 商家ID
    int sellerId;
    // 商品名称
    String name;
    // 产地
    String place;

    //价格
    double price;

    // 剩余数量
    int avail;

    //图片信息
    List<ProductImage> images;

    //商品描述
    String desc;

    //发布时间
    Date publishDate;

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvail() {
        return avail;
    }

    public void setAvail(int avail) {
        this.avail = avail;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
