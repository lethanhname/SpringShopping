package com.example.shopping.services;

import com.example.shopping.exception.CategoryNotFoundException;
import com.example.shopping.exception.ProductNotFoundException;
import com.example.shopping.entities.Product;

public interface ProductService {
  public Product save(Product product) throws CategoryNotFoundException;
  public Product findById(Long id) throws ProductNotFoundException;
}
