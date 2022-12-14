package com.example.shopping.product.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.shopping.product.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
   @Mapping(target = "id", ignore = true)
   @Mapping(target = "products", ignore = true)
   @Mapping(target = "updatedDate", ignore = true)
   @Mapping(target = "createdDate", ignore = true)
   void updateEntity(CategoryUpdateRequest request, @MappingTarget Category category);
}