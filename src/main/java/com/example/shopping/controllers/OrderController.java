package com.example.shopping.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.order.entities.LineItem;
import com.example.shopping.order.entities.Order;
import com.example.shopping.order.services.OrderService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;


  @PostMapping
  public ResponseEntity<Order> create(@RequestBody  @Valid Order request) {
		var order = orderService.create(request);
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}

  @PutMapping(value="{id}")
	public ResponseEntity<LineItem> addProduct(@PathVariable("id") Long id, 
                                          @RequestParam("idProduct") Long idProduct,
                                          @RequestParam("quantity") Integer quantity) {
		var order = orderService.addProduct(id, idProduct, quantity);
		return new ResponseEntity<LineItem>(order, HttpStatus.CREATED);
	}
  @GetMapping()
  public ResponseEntity<List<Order>> findAll() {
    var response = orderService.findAll();
    return ResponseEntity.ok(response) ;
  }
}
