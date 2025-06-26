package com.example.hopeIDontFlop.controller;

import com.example.hopeIDontFlop.model.Category;
import com.example.hopeIDontFlop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/category")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/" )
public class CategoryController {
    // data field
    private final CategoryService categoryService;

    // get all category
    @GetMapping()
    public List<Category> getAllCategory() {return  categoryService.getAllCategory();}

}
