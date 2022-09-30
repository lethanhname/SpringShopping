package com.example.shopping.services;

import java.util.List;

import com.example.shopping.dtos.CategoryUpdateRequest;
import com.example.shopping.entities.Category;

public interface CategoryService {
  public Category create(Category cat);
  public Category update(Long id, CategoryUpdateRequest request);
  public void delete(Long id);
  public List<Category> findAll();
  public Category findById(Long id);
}
