package com.naoh.Fruit.Data;

import java.util.Date;

/**
 * 商品降价记录
 * Created by Administrator on 2016/5/26.
 */
public class ProductPriceReductionRecord {
    // 降价ID
    int reductionRecordId;
    // 商品ID
    int productId;
    // 原价
    double originPrice;
    // 现价
    double currentPrice;

    // 降价时间
    Date date;

    public int getReductionRecordId() {
        return reductionRecordId;
    }

    public void setReductionRecordId(int reductionRecordId) {
        this.reductionRecordId = reductionRecordId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
