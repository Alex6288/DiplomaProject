package com.example.diploma.service;

import com.example.diploma.entity.*;
import com.example.diploma.repas.IOrderProductRep;
import com.example.diploma.repas.IOrderRep;
import com.example.diploma.repas.IProductRatingRep;
import com.example.diploma.repas.IUserRep;
import com.example.diploma.service.interfaces.IUserCabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class UserCabinetService implements IUserCabinetService {

    public static final long DEFAULT_GUEST_ID = 10;

    @Autowired
    IUserRep userRep;

    @Autowired
    IOrderRep orderRep;

    @Autowired
    IOrderProductRep orderProductRep;

    @Autowired
    IProductRatingRep productRatingRep;

    @Override
    public User getUserByLogin(String login) throws NoSuchElementException {
        return userRep.findByLogin(login).orElseThrow();
    }

    @Override
    public void updateUserInfo(User user) {
        User userUpdate = getUserByLogin(user.getLogin());

        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setMiddleName(user.getMiddleName());
        userUpdate.setCityName(user.getCityName());
        userUpdate.setCountryName(user.getCountryName());
        userUpdate.setPhone(user.getPhone());
        userRep.save(userUpdate);
    }

    @Override
    public List<Orders> getAllOrders(User user) {
        return orderRep.findAllByUser(user);
    }

    @Override
    public Set<OrderProduct> getDetailOrder(Long orderNum) {
        return orderProductRep.findAllByOrderNum(orderNum);
    }

    @Override
    public Long getGuestId() {
        return DEFAULT_GUEST_ID;
    }

    @Override
    public void putProductMark(int mark, Long productId, Long orderNum) {
        OrderProduct oPr = orderProductRep.findAllByOrderNum(orderNum).stream()
                .filter(orderProduct -> orderProduct.getProduct().getId() == productId)
                .findAny().get();
        oPr.setMark(mark);
        orderProductRep.save(oPr);
        Product product = oPr.getProduct();
        ProductRating productRating = product.getProductRating();
        productRating.setQuantityMark(productRating.getQuantityMark() + 1);
        productRating.setSumMark(productRating.getSumMark() + mark);
        productRating.setAv_mark();
        productRatingRep.save(productRating);
    }
}
