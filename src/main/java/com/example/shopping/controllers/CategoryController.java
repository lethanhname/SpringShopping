package com.example.shopping.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.models.Category;
import com.example.shopping.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @PostMapping("/add")
  public ResponseEntity<Category> add(@RequestBody @Valid Category category) {
    var newCat = categoryService.save(category);
    return new ResponseEntity<>(newCat, HttpStatus.CREATED) ;
  }

  @GetMapping("")
  public ResponseEntity<List<Category>> findByName() {
    var cats = categoryService.findAll();
    return ResponseEntity.ok(cats) ;
  }
}
