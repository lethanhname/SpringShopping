package com.example.shopping.product.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.shopping.product.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
   void updateEntity(ProductUpdateRequest request, @MappingTarget Product product);
}