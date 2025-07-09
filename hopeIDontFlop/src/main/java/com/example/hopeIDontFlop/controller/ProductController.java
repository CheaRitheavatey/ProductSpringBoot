package com.example.hopeIDontFlop.controller;

import com.example.hopeIDontFlop.dto.ProductDto;
import com.example.hopeIDontFlop.model.Product;
import com.example.hopeIDontFlop.repository.ProductRepository;
import com.example.hopeIDontFlop.service.ProductService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productdto) {
        Product product = maptoEnity(productdto);
        ProductDto saved = productService.createProduct(product);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    private Product maptoEnity (ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());

        return product;
    }
}
