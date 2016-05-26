package com.naoh.Fruit.Data;

/**
 * 商品的图片
 * Created by Administrator on 2016/5/26.
 */
public class ProductImage {
    int imageId;
    int productId;
    String path;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
