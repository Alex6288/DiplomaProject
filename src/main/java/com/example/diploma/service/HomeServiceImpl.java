package com.example.diploma.service;

import com.example.diploma.entity.News;
import com.example.diploma.entity.Product;
import com.example.diploma.repas.INewsRep;
import com.example.diploma.repas.IProductRep;
import com.example.diploma.service.interfaces.IHomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements IHomePageService {

    private final int MAX_POPULAR_PRODUCT_ON_PAGE = 5;
    private final String SORTED_POPULAR_PRODUCT_FIELD_NAME = "quantityBy";

    private final int MAX_NEWS_ON_PAGE = 5;
    private final String SORTED_NEWS_FIELD_NAME = "id";


    @Autowired
    IProductRep productRep;
    @Autowired
    INewsRep newsRep;

    @Override
    public Page<News> findTopNews() {
        Sort sort =  Sort.by(SORTED_NEWS_FIELD_NAME).descending();
        Pageable pageable = PageRequest.of(0, MAX_NEWS_ON_PAGE, sort);
        return newsRep.findAll(pageable);
    }

    @Override
    public Page<Product> findFirstTopProducts() {
        Sort sort =  Sort.by(SORTED_POPULAR_PRODUCT_FIELD_NAME).descending();
        Pageable pageable = PageRequest.of(0, MAX_POPULAR_PRODUCT_ON_PAGE, sort);
        return productRep.findAll(pageable);
    }


}
