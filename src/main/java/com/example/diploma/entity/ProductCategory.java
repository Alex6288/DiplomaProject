package com.example.diploma.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    public ProductCategory() {
    }

    public ProductCategory(Product product, Category category) {
        this.product = product;
        this.category = category;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public Category getCategory() {
        return category;
    }
}
