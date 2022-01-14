package com.example.diploma.service.interfaces;

import com.example.diploma.entity.OrderProduct;
import com.example.diploma.entity.Orders;
import com.example.diploma.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IUserCabinetService {
    User getUserByLogin(String login);
    void updateUserInfo(User user);
    List<Orders> getAllOrders(User user);
    Set<OrderProduct> getDetailOrder( Long orderNum);
    Long getGuestId();
    void putProductMark(int mark, Long productId, Long orderNum);
}
