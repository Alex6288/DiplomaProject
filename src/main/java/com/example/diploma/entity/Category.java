package com.example.diploma.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String categoryName;

    public Category() {
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
