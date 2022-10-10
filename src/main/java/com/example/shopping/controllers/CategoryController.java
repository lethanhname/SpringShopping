package com.example.shopping.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.product.dto.CategoryUpdateRequest;
import com.example.shopping.product.entities.Category;
import com.example.shopping.product.services.CategoryService;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @PostMapping()
  public ResponseEntity<Category> create(@RequestBody @Valid Category category) {
    var newCat = categoryService.create(category);
    return new ResponseEntity<>(newCat, HttpStatus.CREATED) ;
  }

  @GetMapping()
  public ResponseEntity<List<Category>> findAll() {
    var cats = categoryService.findAll();
    return ResponseEntity.ok(cats) ;
  }
  @GetMapping(value = "/{id}")
  public Category findById(@PathVariable("id") Long id) {
    var cat = categoryService.findById(id);
    return cat;
  }
  
  @PutMapping("/{id}")
  public Category update(@PathVariable Long id, @Valid @RequestBody CategoryUpdateRequest request) {
      return categoryService.update(id, request);
  }
  
  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
      categoryService.delete(id);
      return ResponseEntity.ok().build();
  }


}
