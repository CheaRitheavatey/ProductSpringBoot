package com.example.hopeIDontFlop.controller;

import com.example.hopeIDontFlop.dto.ProductDto;
import com.example.hopeIDontFlop.model.Product;
import com.example.hopeIDontFlop.repository.ProductRepository;
import com.example.hopeIDontFlop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/product")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class ProductController {
    // data field
    private final ProductService productService;
    private final ProductRepository productRepository;

    // get all product
    @GetMapping()
    public List<ProductDto> getAllProduct() {return  productService.getAllProduct();}

    // get api/product/{id}
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    // get all product by category
    @GetMapping(path = "category/{categoryId}")
    public  List<ProductDto> getAllProductByCategory(@PathVariable Long categoryId) {
        return  productService.getProductByCategoryId(categoryId);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
}
