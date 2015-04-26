package com.renovator.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by darlingtld on 2015/4/3.
 */
@Entity
@Table(name = "product")
public class Product {
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private double price;
    @Column(name = "discount")
    private float discount;
    @Column(name = "ts")
    private Date ts;
    @Column(name = "picurl")
    private String picurl;
    @Column(name = "picurl1")
    private String picurl1;
    @Column(name = "picurl2")
    private String picurl2;
    @Column(name = "picurl3")
    private String picurl3;
    @Column(name = "html")
    private String html;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", ts=" + ts +
                ", picurl='" + picurl + '\'' +
                ", picurl1='" + picurl1 + '\'' +
                ", picurl2='" + picurl2 + '\'' +
                ", picurl3='" + picurl3 + '\'' +
                ", html='" + html + '\'' +
                '}';
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPicurl3() {
        return picurl3;
    }

    public void setPicurl3(String picurl3) {
        this.picurl3 = picurl3;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }


    public String getPicurl1() {
        return picurl1;
    }

    public void setPicurl1(String picurl1) {
        this.picurl1 = picurl1;
    }

    public String getPicurl2() {
        return picurl2;
    }

    public void setPicurl2(String picurl2) {
        this.picurl2 = picurl2;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public class Category {
        public static final String CATEGORY_SHOE = "鞋类";
        public static final String CATEGORY_COAT = "皮衣";
        public static final String CATEGORY_BAG = "包类";
        public static final String CATEGORY_LEATHER = "皮具";
    }

}
