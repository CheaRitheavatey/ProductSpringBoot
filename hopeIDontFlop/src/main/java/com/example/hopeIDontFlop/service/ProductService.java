package com.example.hopeIDontFlop.service;

import com.example.hopeIDontFlop.dto.ProductDto;
import com.example.hopeIDontFlop.exception.ResourceNotFoundException;
import com.example.hopeIDontFlop.model.Product;
import com.example.hopeIDontFlop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    // data field
    private ProductRepository productRepository;


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
        return mapToDto(savedProduct);
    }




}
