package com.example.diploma.repas;

import com.example.diploma.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INewsRep extends JpaRepository<News, Long> {
}
