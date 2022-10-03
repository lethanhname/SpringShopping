package com.example.shopping.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.exception.ResourceNotFoundException;
import com.example.shopping.filters.FilterBuilderService;
import com.example.shopping.filters.FilterCondition;
import com.example.shopping.product.dto.ProductUpdateRequest;
import com.example.shopping.product.entities.Product;
import com.example.shopping.product.services.ProductService;


@RestController
@RequestMapping("api/v1/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private FilterBuilderService filterBuilderService;
  
  @PostMapping()
  public ResponseEntity<Product> create(@RequestBody @Valid Product product)  throws ResourceNotFoundException {
    var newProd = productService.create(product);
    return new ResponseEntity<>(newProd, HttpStatus.CREATED) ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> findById(@PathVariable Long id) throws ResourceNotFoundException {
    var prod = productService.findById(id);
    return ResponseEntity.ok(prod) ;
  }

  @PutMapping("/{id}")
  public Product update(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequest request) {
      return productService.update(id, request);
  }


  @DeleteMapping("{id}")
  public ResponseEntity<?> deletePost(@PathVariable Long id) {
      productService.delete(id);
      return ResponseEntity.ok().build();
  }

  @GetMapping()
  public Page<Product> findAll(
          @RequestParam(value = "page", defaultValue = "0") int page,
          @RequestParam(value = "size", defaultValue = "20") int size,
          @RequestParam(value = "filters", required = false) String filters,
          @RequestParam(value = "orders", required = false) String orders) throws ResourceNotFoundException {
          
    Pageable pageable = filterBuilderService.getPageable(size, page, orders);
    List<FilterCondition> conditions = filterBuilderService.createFilterCondition(filters);

    var prod = productService.findAll(pageable, conditions);
    return prod;
  }
}
