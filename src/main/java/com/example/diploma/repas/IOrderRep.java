package com.example.diploma.repas;

import com.example.diploma.entity.Orders;
import com.example.diploma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRep extends JpaRepository<Orders, Long> {
    List<Orders> findAllByUser(User user);
}
