package com.renovator.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by darlingtld on 2015/4/3.
 */
@Entity
@Table(name = "service")
public class Service {
    @Id
    private int id;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "status")
    private String status;
    @Column(name = "type")
    private String type;
    @Column(name = "price")
    private double price;
    @Column(name = "ts")
    private Date ts;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "to_address")
    private String toAddress;

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", ts=" + ts +
                ", user=" + user +
                ", product=" + product +
                ", toAddress='" + toAddress + '\'' +
                '}';
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public class Status {
        public static final String ORDERED = "已下单";
        public static final String PAID = "已付款";
        public static final String OUT_OF_FACTORY = "已发货";
        public static final String DELIVERING = "派送中";
        public static final String CHECKED = "已收货";
    }
}

