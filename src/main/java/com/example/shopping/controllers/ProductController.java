package com.example.shopping.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.models.Product;
import com.example.shopping.services.CategoryService;
import com.example.shopping.services.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private CategoryService categoryService;

  @PostMapping()
  public ResponseEntity<Product> add(@RequestBody @Valid Product product) {
    var cat = categoryService.findById(product.getCategory().getId());
    product.setCategory(cat);
    var newProd = productService.save(product);
    return new ResponseEntity<>(newProd, HttpStatus.CREATED) ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> findById(@PathVariable Long id) {
    var prod = productService.findById(id);
    return ResponseEntity.ok(prod) ;
  }
}