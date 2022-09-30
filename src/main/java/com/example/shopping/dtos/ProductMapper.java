package com.example.shopping.dtos;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.shopping.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
   void updateEntity(ProductUpdateRequest request, @MappingTarget Product product);
}