package com.naoh.Fruit.Data;

import java.util.Date;

/**
 * 交易记录
 * Created by Administrator on 2016/5/26.
 */
public class TradingRecord {
    // 交易记录ID
    int id;
    // 买家ID
    int userId;
    // 卖家ID
    int sellerId;
    // 交易商品ID
    int productId;
    // 交易数量
    int tradeCount;
    // 交易日期
    Date tradeDate;
    // 交易金额
    double money;

    // 订单ID，一个订单可以有多个商品交易记录
    String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
