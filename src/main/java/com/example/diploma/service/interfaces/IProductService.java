package com.example.diploma.service.interfaces;

import com.example.diploma.entity.Category;
import com.example.diploma.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct();
    Product getProductById(Long id);
    Category getCategoryById(Long id);
    void addProduct(Product product);
    void deleteProductById(Long id);
    Page<Product> findPaginated(int pageNo, int pageSize);
    Page<Product> findPaginatedSearch(int pageNo, int pageSize
            , String nameProduct
            , List<Object> brandNames
            , List<Object> categoryNames
            , String priceStart
            , String priceEnd);
}
