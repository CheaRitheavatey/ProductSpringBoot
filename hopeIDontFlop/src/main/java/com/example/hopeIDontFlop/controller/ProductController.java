package com.example.hopeIDontFlop.controller;

import com.example.hopeIDontFlop.model.Product;
import com.example.hopeIDontFlop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/product")
@AllArgsConstructor
public class ProductController {
    // data field
    private ProductService productService;

    // get all product
    @GetMapping()
    public List<Product> getAllProduct() {return  productService.getAllProduct();}

    // get all product by category
    @GetMapping(path = "category/{categoryId}")
    public  List<Product> getAllProductByCategory(@PathVariable Long categoryId) {return  productService.getProductById(categoryId);}
}
