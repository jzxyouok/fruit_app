package com.naoh.Fruit.Data;

import java.util.LinkedList;
import java.util.List;

public class OrderBean {
    private String orderId;
    private int userId;
    private int sellerId;
    private int payed; // 1已支付，0：未支付
    private int hasSended;
    private String address;

    /**
     * 一个订单可能购买多个商品，这里存储购买的记录
     */
    private List<TradingRecord> recordList;

    public OrderBean() {
        this.recordList = new LinkedList<TradingRecord>();
    }

    public List<TradingRecord> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<TradingRecord> recordList) {
        this.recordList = recordList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public int getPayed() {
        return payed;
    }

    public void setPayed(int payed) {
        this.payed = payed;
    }

    public int getHasSended() {
        return hasSended;
    }

    public void setHasSended(int hasSended) {
        this.hasSended = hasSended;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
