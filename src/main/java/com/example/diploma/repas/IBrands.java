package com.example.diploma.repas;

import com.example.diploma.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IBrands extends JpaRepository<Brand, Long> {
    @Query("SELECT DISTINCT b.brandName FROM Brand b ")
    Iterable<String> getAllDistinctBrandName();

    @Query("select b.imgFilePath from Brand b")
    Iterable<String> getAllLogoImgPath();

    Optional<Brand> findByBrandName(String name);
    Brand getBrandByBrandName(String name);

    @Modifying
    @Transactional
    @Query("update Brand b set b.brandName = :brandName, b.imgFilePath = :imgPath where b.id = :brandId")
    int updateBrand(@Param("brandId") Long brandId,
                       @Param("brandName") String brandName,
                       @Param("imgPath") String imgPath);

    @Modifying
    @Transactional
    @Query("update Brand b set b.brandName = :brandName where b.id = :brandId")
    int updateBrand(@Param("brandId") Long brandId,
                    @Param("brandName") String brandName);
}
