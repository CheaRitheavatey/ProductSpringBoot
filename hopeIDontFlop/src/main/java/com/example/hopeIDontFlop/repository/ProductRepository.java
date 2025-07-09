package com.example.hopeIDontFlop.repository;

import com.example.hopeIDontFlop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long id);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCaseAndCategory_Id(String name, Long categoryId, Pageable pageable);
    Page<Product> findByCategoryId(Long id, Pageable pageable);
    Page<Product> searchByNameAndCategory(String name, Long id, Pageable pageable);

}
