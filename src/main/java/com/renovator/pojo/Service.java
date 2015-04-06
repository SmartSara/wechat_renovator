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
    private int orderId;
    @Column(name = "type")
    private String type;
    @Column(name = "price")
    private double price;
    @Column(name = "ts")
    private Date ts;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

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

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", ts=" + ts +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
