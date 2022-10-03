package com.example.shopping.order.services;

import java.util.List;

import com.example.shopping.order.entities.LineItem;
import com.example.shopping.order.entities.Order;

public interface OrderService {
  Order create(Order request);
  LineItem addProduct(Long orderId, Long productId, Integer quantity);
  public List<Order> findAll();
}
