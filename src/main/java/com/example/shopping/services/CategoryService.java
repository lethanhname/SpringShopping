package com.example.shopping.services;

import java.util.List;

import com.example.shopping.entities.Category;

public interface CategoryService {
  public Category save(Category cat);
  public List<Category> findAll();
  public Category findById(Long id);
}
