package com.example.shopping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.exception.CategoryNotFoundException;
import com.example.shopping.exception.ProductNotFoundException;
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
  public Product save(Product product) throws CategoryNotFoundException{
    var cat = product.getCategory();
    if(cat == null){
      throw new CategoryNotFoundException();
    }
    var catId = cat.getId();
    if(catId == null){
      throw new CategoryNotFoundException();
    }
    var catdb = categoryRepository.findById(catId);
    if(catdb.isEmpty()){
      throw new CategoryNotFoundException();
    }
    product.setCategory(catdb.get());
    return productRepository.save(product);
  }

  @Override
  public Product findById(Long id) throws ProductNotFoundException {
    var product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
    return product;
  }
  
}
