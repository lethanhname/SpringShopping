package com.example.shopping.product.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping.product.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
  Page<Product> findAll(Specification<Product> spec, Pageable pageable);
}
