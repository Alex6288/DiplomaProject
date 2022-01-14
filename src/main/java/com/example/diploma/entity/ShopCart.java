package com.example.diploma.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@Entity
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShopCart {

    private Set<ShopCartItem> shopCartItemSet = new HashSet<>();

    public Set<ShopCartItem> getShopCartItemSet() {
        return shopCartItemSet;
    }

    public void setShopCartItemSet(Set<ShopCartItem> shopCartItemSet) {
        this.shopCartItemSet = shopCartItemSet;
    }

    public int getNumProductByProductId(Long productId){
        System.out.println();
        System.out.println("getNumProductByProductId");
        System.out.println("productId =" + productId);
        int num = shopCartItemSet.stream()
                .filter(shopCartItem -> shopCartItem.getProductId() == productId)
                .findAny()
                .get().getNumProduct();
        System.out.println("numProduct = " + num);
        return shopCartItemSet.stream()
                .filter(shopCartItem -> shopCartItem.getProductId() == productId)
                .findAny()
                .get().getNumProduct();
    }

    @Override
    public String toString() {
        return "ShopCart{" +
                "shopCartItemSet=" + shopCartItemSet.toString() +
                '}';
    }
}
