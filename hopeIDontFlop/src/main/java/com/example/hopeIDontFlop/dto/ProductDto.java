package com.example.hopeIDontFlop.dto;


import com.example.hopeIDontFlop.model.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;
    private String imgUrl;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be more than 0")
    private double price;

    @NotNull(message = "Category is required")
    private Category category;
}
