package com.example.diploma.service;

import com.example.diploma.entity.*;
import com.example.diploma.entity.exception.ProductNotEnoughException;
import com.example.diploma.repas.*;
import com.example.diploma.service.interfaces.IProductShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class ProductShopCartService implements IProductShopCart {

    @Autowired
    IOrderRep  orderRep;
    @Autowired
    IProductRep productRep;
    @Autowired
    IOrderProductRep orderProductRep;
    @Autowired
    IUserRep userRep;

    @Autowired
    ShopCart shopCart;

    @Override
    public Set<ShopCartItem> getShopCartListByUserId(Long userId) throws NoSuchElementException {
        return null; //shopCartItemRep.findAllByUserId(userId);
    }

    @Override
    synchronized public void saveOrderShopCart(Long userId, String contact, String phone, String email, String address) throws NoSuchElementException, ProductNotEnoughException {
        //сохраняем каждый елемент покупки  продуктах в базу пользователя и удаляем из корзины
        Long orderNum = Orders.getOrderCount();
        for(ShopCartItem item: shopCart.getShopCartItemSet()){
            Product product = productRep.getById(item.getProductId());
             if (product.getQuantity() < item.getNumProduct()) {
                 throw new ProductNotEnoughException("Недостаточно продукта - " + product.getProductName() + ", уменьшите количество");
             }
            product.setQuantity(product.getQuantity() - item.getNumProduct());
            product.setQuantityBy(product.getQuantityBy() + item.getNumProduct());
            OrderProduct orderProduct = new OrderProduct(product, item.getNumProduct(), orderNum);
            orderProductRep.save(orderProduct);
        }
        // обнуляем корзину
        shopCart.getShopCartItemSet().clear();
        //сохраняем заказ
        Set<OrderProduct> orderProducts = orderProductRep.findAllByOrderNum(orderNum);
        Integer totalOrderPrice = orderProductRep.sumTotalPriceByOrderNum(orderNum);
        User user = userRep.getById(userId);
        Orders orders = new Orders(orderProducts, user, contact, phone, email, address, totalOrderPrice,orderNum);
        orderRep.save(orders);
        // увеличиваем счетчик заказов
        Orders.increaseOrderCount();
    }

    /// shop cart session
    @Override
    public void addProductByIdInShopCart(Long productId) throws NoSuchElementException{
       if  (!shopCart.getShopCartItemSet().stream().anyMatch(shopCartItem -> shopCartItem.getProductId() == productId))
            shopCart.getShopCartItemSet().add(new ShopCartItem(productId, 1));
    }

    @Override
    public void deleteProductByIdInShopCart(Long productId) throws NoSuchElementException{
        shopCart.getShopCartItemSet().removeIf(item -> item.getProductId() == productId);
    }

    @Override
    public void updateNumProductByIdInShopCart(Long productId, int num) {
        ShopCartItem newItem = new ShopCartItem(productId, num);
        deleteProductByIdInShopCart(productId);
        shopCart.getShopCartItemSet().add(newItem);
    }

    @Override
    public Set<ShopCartItem> getShopCartList() {
        return shopCart.getShopCartItemSet();
    }

    public Set<Product> getProductList(){
        Set<Product> productSet = new HashSet<>();
        for (ShopCartItem item : shopCart.getShopCartItemSet()){
            productSet.add(productRep.getById(item.getProductId()));
        }
        return productSet;
    }

    public ShopCart getShopCart(){
        return this.shopCart;
    }

}
