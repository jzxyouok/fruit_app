package com.naoh.Fruit.Data;

/**
 * 商品分类
 * Created by Administrator on 2016/5/26.
 */
public class Category {
    // 分类ID
    int id;
    // 分类名称
    String name;
    // image（使用程序中的图），这里只保存R id
    int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
