package com.example.hopeIDontFlop.repository;

import com.example.hopeIDontFlop.model.ProductButPractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductButPracticeRepo extends JpaRepository<ProductButPractice, Long> {
}
