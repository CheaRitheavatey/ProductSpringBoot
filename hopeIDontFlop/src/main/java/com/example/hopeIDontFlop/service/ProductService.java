package com.example.hopeIDontFlop.service;

import com.example.hopeIDontFlop.dto.ProductDto;
import com.example.hopeIDontFlop.exception.ResourceNotFoundException;
import com.example.hopeIDontFlop.model.Category;
import com.example.hopeIDontFlop.model.Product;
import com.example.hopeIDontFlop.repository.CategoryRepository;
import com.example.hopeIDontFlop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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


    // update product
    public ProductDto updateProduct(Long id, ProductDto dto) {
        // if id not exist throw resourceexception
        Product exitProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found" ));

        exitProduct.setName(dto.getName());
        exitProduct.setDescription(dto.getDescription());
        exitProduct.setPrice(dto.getPrice());

        Category category = categoryRepository.findById(dto.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + dto.getCategory().getId() + "not found"));

        exitProduct.setCategory(category);

        Product updated = productRepository.save(exitProduct);
        return mapToDto(updated);
    }

    // delete product
    public void deleteProduct(Long id) {
        // if the product id exist then delete else throw exception
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));

        productRepository.delete(product);
    }

    // pagination
    public Page<ProductDto> searchProducts(String name, int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<Product> pageResult = productRepository.findByNameContainingIgnoreCase(name, pageable);
        return pageResult.map(this::mapToDto);
    }

    public Page<ProductDto> getAllPaginated(int page, int size) {
        // Create a pagination request (e.g., page 0 with 5 items)
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        // Get the page of products
        Page<Product> productPage = productRepository.findAll((org.springframework.data.domain.Pageable) pageable);

        // Convert each Product into ProductDto and return the paginated result
        return productRepository.findAll((org.springframework.data.domain.Pageable) pageable).map(this::mapToDto);
    }

}
