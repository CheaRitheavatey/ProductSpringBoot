package com.example.hopeIDontFlop.service;

import com.example.hopeIDontFlop.model.Category;
import com.example.hopeIDontFlop.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    // data field
    private final CategoryRepository categoryRepository;
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
