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
@Table(name = "service")
public class Service {
    @Id
    private int id;
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "type")
    private String type;
    @Column(name = "price")
    private double price;
    @Column(name = "ts")
    private Date ts;
    @Column(name = "user_id")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
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

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", ts=" + ts +
                ", userId=" + userId +
                '}';
    }
}
