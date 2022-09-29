package com.example.shopping.services;

import com.example.shopping.entities.Product;
import com.example.shopping.exception.ResourceNotFoundException;

public interface ProductService {
  public Product save(Product product) throws ResourceNotFoundException;
  public Product findById(Long id) throws ResourceNotFoundException;
}
