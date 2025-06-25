package com.example.hopeIDontFlop.controller;

import com.example.hopeIDontFlop.model.ProductButPractice;
import com.example.hopeIDontFlop.service.ProductButPracticeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/pro")
public class ProductButPracticeController {
// data field
    private final ProductButPracticeService productButPracticeService;

    // GET method
    @GetMapping
    public List<ProductButPractice> getAllProductButPractice() {
        return productButPracticeService.getAllProductButPractice();
    }

    // GET method by Id
    @GetMapping(path = "/{id}" )
    public ResponseEntity<ProductButPractice> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productButPracticeService.getProductButPracticeById(id));
    }

    // POST method
    @PostMapping
    public ResponseEntity<ProductButPractice> createProduct(@RequestBody ProductButPractice productButPractice) {
        ProductButPractice created = productButPracticeService.createProduct(productButPractice);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
