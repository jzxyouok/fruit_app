package com.naoh.Fruit.Data;

/**
 * 卖家信息
 * Created by Administrator on 2016/5/26.
 */
public class SellerBean {
    // 卖家ID
    int id;
    // 卖家名称
    String name;
    // 卖家电话
    String telephone;
    // 卖家住址
    String address;
    // 密码
    String password;

    // 头像路径
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
