package com.naoh.Fruit.Data;

/**
 * Created by Administrator on 2016/5/26.
 */
public class ProductWatchRecord {
    // 关注ID
    int watchRecordId;
    // 买家ID
    int userID;
    // 商品ID
    int productId;

    public int getWatchReordId() {
        return watchRecordId;
    }

    public void setWatchReordId(int watchReordId) {
        this.watchRecordId = watchReordId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
