package com.example.shopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping.models.Category;

@Repository
public interface CategoryRepository extends  JpaRepository<Category, Long>{
}