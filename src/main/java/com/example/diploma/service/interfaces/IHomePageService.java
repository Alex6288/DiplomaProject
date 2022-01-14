package com.example.diploma.service.interfaces;

import com.example.diploma.entity.News;
import com.example.diploma.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

public interface IHomePageService {
    Page<Product> findFirstTopProducts();
    Page<News> findTopNews();
}
