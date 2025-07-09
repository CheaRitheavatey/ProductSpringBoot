package com.example.hopeIDontFlop.service;

import com.example.hopeIDontFlop.dto.ProductDto;
import com.example.hopeIDontFlop.exception.ResourceNotFoundException;
import com.example.hopeIDontFlop.model.Category;
import com.example.hopeIDontFlop.model.Product;
import com.example.hopeIDontFlop.repository.CategoryRepository;
import com.example.hopeIDontFlop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    // data field
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public List<ProductDto> getAllProduct() {return productRepository.findAll()
            //  stream = process a collection of objects one by one
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());

        return dto;

    }

    private Product mapToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());

        Category category = categoryRepository.findById(productDto.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category with ID: " + productDto.getId() + " not found"));

        product.setCategory(category);
        return product;

    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with " + id + " is not found"));
        return mapToDto(product);
    }

    public List<ProductDto> getProductByCategoryId(Long id) {
        return productRepository.findByCategoryId(id)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ProductDto createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        Product saved = productRepository.save(product);
        return mapToDto(savedProduct);
    }




}
