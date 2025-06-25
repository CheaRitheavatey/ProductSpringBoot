package com.example.hopeIDontFlop.service;

import com.example.hopeIDontFlop.model.Product;
import com.example.hopeIDontFlop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    // data field
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {return productRepository.findAll();}
    public List<Product> getProductById(Long id) {return productRepository.findByCategoryId(id);}
}
