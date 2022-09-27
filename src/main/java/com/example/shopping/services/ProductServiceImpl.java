package com.example.shopping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.exception.ProductNotFoundException;
import com.example.shopping.models.Product;
import com.example.shopping.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{


  @Autowired
  private ProductRepository productRepository;

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product findById(Long id) throws ProductNotFoundException {
    var product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
    return product;
  }
  
}
