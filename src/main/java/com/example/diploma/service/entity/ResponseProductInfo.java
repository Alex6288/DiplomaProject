package com.example.diploma.service.entity;

import com.example.diploma.entity.Product;

import java.util.List;

public class ResponseProductInfo {

    private List<Product> productList;

    private SearchData searchData;

    private int totalPages;
    private long totalProduct;
    private int currentPage;
    private Integer pageSize;

    public ResponseProductInfo() {
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ResponseProductInfo(List<Product> productList, int totalPages, long totalProduct, int currentPage, Integer pageSize) {
        this.productList = productList;
        this.totalPages = totalPages;
        this.totalProduct = totalProduct;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public ResponseProductInfo(List<Product> productList, SearchData searchData, int totalPages, long totalProduct, int currentPage, Integer pageSize) {
        this.productList = productList;
        this.searchData = searchData;
        this.totalPages = totalPages;
        this.totalProduct = totalProduct;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public SearchData getSearchData() {
        return searchData;
    }

    public void setSearchData(SearchData searchData) {
        this.searchData = searchData;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalProduct(long totalProduct) {
        this.totalProduct = totalProduct;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalProduct() {
        return totalProduct;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }


}
