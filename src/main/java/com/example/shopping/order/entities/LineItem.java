package com.example.shopping.order.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.example.shopping.product.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "order_items")
@Data
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
public class LineItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "productId")
  @NotNull
	private Product product;

  @ManyToOne(optional = false)
  @JoinColumn(name = "orderId")
	private Order order;
	
  @Column(nullable = false)
  @NotNull
  @Min(value = 1, message = "min 1 is required")
  private Integer quantity;

  @Column(name = "price", nullable = false, precision = 10)
	private BigDecimal price;
}
