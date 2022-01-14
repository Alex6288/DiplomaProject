package com.example.diploma.service.entity;

import java.util.List;

public class SearchData {
    private List<Object> brandNames;
    private String priceStart;
    private String priceEnd;
    private List<Object> categoryNames;
    private String findByName;

    public SearchData(List<Object> brandNames, String priceStart, String priceEnd, List<Object> categoryNames, String findByName) {
        this.brandNames = brandNames;
        this.priceStart = priceStart;
        this.priceEnd = priceEnd;
        this.categoryNames = categoryNames;
        this.findByName = findByName;
    }

    public void setBrandNames(List<Object> brandNames) {
        this.brandNames = brandNames;
    }

    public void setPriceStart(String priceStart) {
        this.priceStart = priceStart;
    }

    public void setPriceEnd(String priceEnd) {
        this.priceEnd = priceEnd;
    }

    public void setCategoryNames(List<Object> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public void setFindByName(String findByName) {
        this.findByName = findByName;
    }

    public List<Object> getBrandNames() {
        return brandNames;
    }

    public String getPriceStart() {
        return priceStart;
    }

    public String getPriceEnd() {
        return priceEnd;
    }

    public List<Object> getCategoryNames() {
        return categoryNames;
    }

    public String getFindByName() {
        return findByName;
    }
}
