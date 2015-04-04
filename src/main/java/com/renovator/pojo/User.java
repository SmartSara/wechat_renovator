package com.renovator.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by tangld on 2015/3/30.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "contact")
    private String contact;
    @Column(name = "address")
    private String address;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "balance")
    private double balance;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    @Override
    public String toString() {
        return "User{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", birthday=" + birthday +
                ", balance=" + balance +
                '}';
    }
}
