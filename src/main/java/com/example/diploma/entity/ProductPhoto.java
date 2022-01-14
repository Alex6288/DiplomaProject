package com.example.diploma.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class ProductPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String photoPath;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    public ProductPhoto() {
    }

    public ProductPhoto(String photoPath, Product product) {
        this.photoPath = photoPath;
        this.product = product;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public Product getProduct() {
        return product;
    }
}
