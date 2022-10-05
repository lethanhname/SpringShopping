package com.example.shopping.reports;

import java.util.List;

import com.example.shopping.reports.dto.TopProductsResponse;

public interface TopProductService {
  public List<TopProductsResponse> get();
}
