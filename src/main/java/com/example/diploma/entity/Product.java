package com.example.diploma.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private int price;
    private int quantity;
    private int quantityBy;
    private String description_short;
    private String description_full;
    private String mainPhotoPath;

    @OneToOne
    private ProductRating productRating;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Brand brand;

    public Product() {
    }

    public Product(String productName, Brand brand, int price, int quantity, String description_short, String description_full, String mainPhotoPath, Category category) {
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.quantityBy = 0;
        this.description_short = description_short;
        this.description_full = description_full;
        this.mainPhotoPath = mainPhotoPath;
        this.category = category;
    }

    public ProductRating getProductRating() {
        return productRating;
    }

    public String getRating(){
        return productRating.getAv_mark();
    }

    public void setProductRating(ProductRating productRating) {
        this.productRating = productRating;
    }

    public Long getId() {
        return id;
    }

    public String getMainPhotoPath() {
        return mainPhotoPath;
    }

    public int getQuantityBy() {
        return quantityBy;
    }

    public void setQuantityBy(int quantityBy) {
        this.quantityBy = quantityBy;
    }

    public void setMainPhotoPath(String mainPhotoPath) {
        this.mainPhotoPath = mainPhotoPath;
    }

    public void setCategory(Category productCategory) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription_short(String description_short) {
        this.description_short = description_short;
    }

    public void setDescription_full(String description_full) {
        this.description_full = description_full;
    }

    public String getProductName() {
        return productName;
    }

    public Brand getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription_short() {
        return description_short;
    }

    public String getDescription_full() {
        return description_full;
    }

    public String getMainPhotoFileName(){
        if (!mainPhotoPath.isEmpty()){
            String[] strs = mainPhotoPath.split("/");
            System.out.println("file name = " + strs[strs.length - 1]);
            return strs[strs.length - 1];
        }
        return "";
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", brandName='" + brand.getBrandName() + '\'' +
                ", price=" + price +
                ", quantityBy=" + quantityBy +
                ", category=" + category +
                '}';
    }
}
