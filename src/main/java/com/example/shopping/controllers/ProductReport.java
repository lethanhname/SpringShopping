package com.example.shopping.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.reports.TopProductService;
import com.example.shopping.reports.dto.TopProductsResponse;

@RestController
@RequestMapping("api/v1/product-reports")
public class ProductReport {

  @Autowired
  private TopProductService topProductService;

  @GetMapping("topSale")
  public ResponseEntity<List<TopProductsResponse>> topProduct() {
    var cats = topProductService.get();
    return ResponseEntity.ok(cats) ;
  }
}
