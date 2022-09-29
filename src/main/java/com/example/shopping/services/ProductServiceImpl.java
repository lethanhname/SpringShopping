package com.example.shopping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.exception.ResourceNotFoundException;
import com.example.shopping.entities.Product;
import com.example.shopping.repositories.CategoryRepository;
import com.example.shopping.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

  
  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Product save(Product product) throws ResourceNotFoundException {
    var cat = product.getCategory();
    if(cat == null){
      throw new ResourceNotFoundException("Category not found");
    }
    var catId = cat.getId();
    if(catId == null){
      throw new ResourceNotFoundException("Category not found");
    }
    var catdb = categoryRepository.findById(catId);
    if(catdb.isEmpty()){
      throw new ResourceNotFoundException("Category not found");
    }
    product.setCategory(catdb.get());
    return productRepository.save(product);
  }

  @Override
  public Product findById(Long id) throws ResourceNotFoundException {
    var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    return product;
  }
  
}
