package com.example.shopping.product.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.exception.DuplicatedException;
import com.example.shopping.product.dto.CategoryMapper;
import com.example.shopping.product.dto.CategoryUpdateRequest;
import com.example.shopping.product.entities.Category;
import com.example.shopping.product.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private CategoryMapper categoryMapper;

  @Override
  public Category create(Category cat) {
    var isExisted = !categoryRepository.findByName(cat.getName()).isEmpty();
    if(isExisted){
      throw new DuplicatedException("Duplicated Name");
    }
    return categoryRepository.save(cat);
  }

  @Override
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Category findById(Long id) {
    return categoryRepository.findById(id).get();
  }

  @Override
  public Category update(Long id, CategoryUpdateRequest request) {
    var cats = categoryRepository.findByName(request.getName());
    if(!cats.isEmpty() && cats.stream().filter(i -> i.getId() != id).findFirst().orElse(null) != null){
      throw new DuplicatedException("Duplicated Name");
    }
    var cat = findById(id);
    categoryMapper.updateEntity(request, cat);
    return categoryRepository.save(cat);
  }

  @Override
  public void delete(Long id) {
    categoryRepository.deleteById(id);
  }
  
}
