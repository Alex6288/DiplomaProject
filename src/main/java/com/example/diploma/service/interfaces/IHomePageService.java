package com.example.diploma.service.interfaces;

import com.example.diploma.entity.News;
import com.example.diploma.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

public interface IHomePageService {
    // todo реализовать метод по рейтингу покупателя или лучше по количеству покупок
    Page<Product> findFirstTopProducts();
    Page<News> findTopNews();
}
