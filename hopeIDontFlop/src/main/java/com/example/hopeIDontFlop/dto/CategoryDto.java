package com.example.hopeIDontFlop.dto;

import java.util.Set;

import com.example.hopeIDontFlop.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;

    private Set<Product> products;
}
