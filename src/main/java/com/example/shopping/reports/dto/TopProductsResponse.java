package com.example.shopping.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopProductsResponse {
  private Long productId;
  private String name;
  private String categoryName;
  private Long salesQuantity;
}
