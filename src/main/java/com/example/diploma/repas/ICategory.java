package com.example.diploma.repas;

import com.example.diploma.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ICategory extends JpaRepository<Category, Long> {
    @Query("SELECT DISTINCT c FROM Category c ")
    Iterable<String> findDistinctCategoryName();

    Optional<Category> findByCategoryName(String categoryName);
    Category getCategoryByCategoryName(String categoryName);

    @Override
    void deleteById(Long aLong);

    @Modifying
    @Transactional
    @Query("update Category c set c.categoryName = :categoryName where c.id = :categoryId")
    int updateCategory(@Param("categoryId") Long categoryId,
                       @Param("categoryName") String categoryName);

}
