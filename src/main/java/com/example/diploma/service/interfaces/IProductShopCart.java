package com.example.diploma.service.interfaces;

import com.example.diploma.entity.exception.ProductNotEnoughException;
import com.example.diploma.entity.ShopCartItem;

import java.util.Set;

public interface IProductShopCart {
    Set<ShopCartItem> getShopCartListByUserId(Long userId);
    void saveOrderShopCart(Long userId, String contact, String phone, String email, String address) throws ProductNotEnoughException;
    void addProductByIdInShopCart(Long productId);
    void deleteProductByIdInShopCart(Long productId);
    void updateNumProductByIdInShopCart(Long productId, int num);
    Set<ShopCartItem> getShopCartList();
}
