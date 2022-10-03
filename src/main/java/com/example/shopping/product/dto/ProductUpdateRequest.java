package com.example.shopping.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.shopping.product.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {

    @NotBlank(message = "Name is required")
    @Length(max = 50, min = 5, message = "Length is between 5 and 50")
    private String name;

    @NotNull
    private Double price;

    private Float discount = 0.0f;

    @NotNull(message = "status is required")
    private ProductStatus status;
}