package com.example.diploma.repas;

import com.example.diploma.entity.Brand;
import com.example.diploma.entity.Category;
import com.example.diploma.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRep extends JpaRepository<Product, Long> {
    Optional<Product> findProductByProductNameAndBrandAndCategory(String productName, Brand brand, Category category);
}
