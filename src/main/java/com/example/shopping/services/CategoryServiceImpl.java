package com.example.shopping.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.models.Category;
import com.example.shopping.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public Category save(Category cat) {
    return categoryRepository.save(cat);
  }

  @Override
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  
}
