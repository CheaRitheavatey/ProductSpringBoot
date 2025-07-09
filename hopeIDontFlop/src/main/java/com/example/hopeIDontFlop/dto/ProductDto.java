package com.example.hopeIDontFlop.dto;


import com.example.hopeIDontFlop.model.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// When converting this Java object to JSON, do not include any fields that are null
@JsonInclude(JsonInclude.Include.NON_NULL) // example for description
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String imgUrl;
    private double price;

    private Category category;
}
