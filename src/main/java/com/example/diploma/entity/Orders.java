package com.example.diploma.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private Set<OrderProduct> orderProducts = new HashSet<>();
    @ManyToOne
    private User user;
    private String contact;
    private String phone;
    private String email;
    private String address;
    private int totalPrice;
    private Long orderNum;

    private static Long orderCount = Long.valueOf(1);

    public static Long getOrderCount() {
        return orderCount;
    }

    synchronized public static void increaseOrderCount(){
        orderCount++;
    }

    public Orders() {
    }

    public Orders(Set<OrderProduct> orderProducts, User user, String contact, String phone, String email, String address, int totalPrice, Long orderNum) {
        this.orderProducts = orderProducts;
        this.user = user;
        this.contact = contact;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
        this.orderNum = orderNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public static void setOrderCount(Long orderCount) {
        Orders.orderCount = orderCount;
    }
}
