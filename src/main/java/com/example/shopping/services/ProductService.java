package com.example.shopping.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.shopping.entities.Product;
import com.example.shopping.exception.ResourceNotFoundException;
import com.example.shopping.filters.FilterCondition;

public interface ProductService {
  public Product save(Product product) throws ResourceNotFoundException;
  public Product findById(Long id) throws ResourceNotFoundException;
  public Page<Product> findAll(Pageable pageable, List<FilterCondition> filters);
}
