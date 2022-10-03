package com.example.shopping.order.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.exception.ResourceNotFoundException;
import com.example.shopping.order.entities.LineItem;
import com.example.shopping.order.entities.Order;
import com.example.shopping.order.repositories.LineItemRepository;
import com.example.shopping.order.repositories.OrderRepository;
import com.example.shopping.product.services.ProductService;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private LineItemRepository lineItemRepository;

  @Autowired
  private ProductService productService;

  @Override
  public Order create(Order request) {
    return orderRepository.save(request);
  }

  @Override
  public LineItem addProduct(Long orderId, Long productId, Integer quantity) {
    var order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    var product = productService.findById(productId);
    var item = LineItem.builder(null, product, order, quantity, BigDecimal.ONE);
    var response = lineItemRepository.save(item);
    return response;
  }

  @Override
  public List<Order> findAll() {
    return orderRepository.findAll();
  }
  
}
