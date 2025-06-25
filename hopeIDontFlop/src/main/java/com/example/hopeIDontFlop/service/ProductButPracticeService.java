package com.example.hopeIDontFlop.service;

import com.example.hopeIDontFlop.model.ProductButPractice;
import com.example.hopeIDontFlop.repository.ProductButPracticeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductButPracticeService {
    // data field
    private final ProductButPracticeRepo productButPracticeRepo;

    // method
    public List<ProductButPractice> getAllProductButPractice() {
        return productButPracticeRepo.findAll();
    }

    // get only 1 product by their id
    public ProductButPractice getProductButPracticeById(Long id) {
        return productButPracticeRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found love"));
    }

    public ProductButPractice createProduct(ProductButPractice productButPractice) {
        return productButPracticeRepo.save(productButPractice);
    }
}
