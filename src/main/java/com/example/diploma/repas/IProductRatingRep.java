package com.example.diploma.repas;

import com.example.diploma.entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRatingRep extends JpaRepository<ProductRating, Long> {
    @Query("select pr.productRating.av_mark from Product pr where pr.id = :productId ")
    String getAvMarkByProductId(Long productId);
}
