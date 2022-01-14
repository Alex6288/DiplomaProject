package com.example.diploma.repas;

import com.example.diploma.entity.ProductCategory;
import org.springframework.data.repository.CrudRepository;

public interface IProductCategory extends CrudRepository<ProductCategory, Long> {
}
