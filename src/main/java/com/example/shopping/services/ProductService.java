package com.example.shopping.services;

import com.example.shopping.exception.ProductNotFoundException;
import com.example.shopping.models.Product;

public interface ProductService {
  public Product save(Product product);
  public Product findById(Long id) throws ProductNotFoundException;
}
