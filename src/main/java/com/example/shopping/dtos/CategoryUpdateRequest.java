package com.example.shopping.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateRequest {

    @NotBlank(message = "Name is required")
    @Length(max = 50, min = 5, message = "Length is between 5 and 50")
    private String name;
}