package com.example.diploma.controller.entity;

import org.springframework.stereotype.Component;

@Component
public class ProductShopConst {

    public static int DEFAULT_PRODUCT_PAGE_SIZE = 2;
    public static int DEFAULT_PAGE_NO = 1;

    public static void setDefaultProductPageSize(int pageSize){
        DEFAULT_PRODUCT_PAGE_SIZE = pageSize;
    }
}
