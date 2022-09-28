package com.example.shopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
