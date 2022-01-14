package com.example.diploma.entity;

public class ShopCartItem {

    private Long id;
    private Long productId;
    private int numProduct;
    private Long userId;

    public ShopCartItem() {
    }

    public ShopCartItem(Long productId, int numProduct ) {
        this.productId = productId;
        this.numProduct = numProduct;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user_id) {
        this.userId = user_id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumProduct() {
        return numProduct;
    }

    public void setNumProduct(int numProduct) {
        this.numProduct = numProduct;
    }

    @Override
    public String toString() {
        return "ShopCartItem{" +
                "product=" + productId +
                ", numProduct=" + numProduct +
                ", userId=" + userId +
                '}';
    }
}
