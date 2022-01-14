package com.example.diploma.entity;

import jdk.jfr.DataAmount;

import javax.persistence.*;

@Entity
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    private int numProduct;
    private int totalPrice;
    private int mark;
    private Long orderNum;

    public OrderProduct(Product product, int numProduct, Long orderNum) {
        this.product = product;
        this.numProduct = numProduct;
        this.orderNum = orderNum;
        this.totalPrice = numProduct * product.getPrice();
    }

    public OrderProduct() {
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumProduct() {
        return numProduct;
    }

    public void setNumProduct(int numProduct) {
        this.numProduct = numProduct;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }
}
