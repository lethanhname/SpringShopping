package com.example.shopping.product.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.shopping.exception.ResourceNotFoundException;
import com.example.shopping.filters.FilterCondition;
import com.example.shopping.product.dto.ProductUpdateRequest;
import com.example.shopping.product.entities.Product;

public interface ProductService {
  public Product create(Product product) throws ResourceNotFoundException;
  public Product update(Long id, ProductUpdateRequest product) throws ResourceNotFoundException;
  public void delete(Long id) throws ResourceNotFoundException;
  public Product findById(Long id) throws ResourceNotFoundException;
  public Page<Product> findAll(Pageable pageable, List<FilterCondition> filters);
}
