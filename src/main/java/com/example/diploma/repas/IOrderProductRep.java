package com.example.diploma.repas;

import com.example.diploma.entity.OrderProduct;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IOrderProductRep extends JpaRepository<OrderProduct, Long> {

    Set<OrderProduct> findAllByOrderNum(Long orderNum);
    @Query("SELECT sum(o.totalPrice) FROM OrderProduct o where o.orderNum = :orderNum")
    Integer sumTotalPriceByOrderNum(Long orderNum);
}
