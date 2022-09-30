package com.example.shopping.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.shopping.exception.ResourceNotFoundException;
import com.example.shopping.filters.FilterCondition;
import com.example.shopping.filters.GenericFilterCriteriaBuilder;
import com.example.shopping.dtos.ProductMapper;
import com.example.shopping.dtos.ProductUpdateRequest;
import com.example.shopping.entities.Product;
import com.example.shopping.repositories.CategoryRepository;
import com.example.shopping.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

  
  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private GenericFilterCriteriaBuilder specBuilder;

  @Autowired
  private ProductMapper productMapper;

  @Override
  public Product create(Product product) throws ResourceNotFoundException {
    var cat = product.getCategory();
    if(cat == null){
      throw new ResourceNotFoundException("Category not found");
    }
    var catId = cat.getId();
    if(catId == null){
      throw new ResourceNotFoundException("Category not found");
    }
    var catdb = categoryRepository.findById(catId);
    if(catdb.isEmpty()){
      throw new ResourceNotFoundException("Category not found");
    }
    product.setCategory(catdb.get());
    return productRepository.save(product);
  }

  @Override
  public Product findById(Long id) throws ResourceNotFoundException {
    var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    return product;
  }
  @Override
	public Page<Product> findAll(Pageable pageable, List<FilterCondition> filters) {
    if(filters == null || filters.isEmpty()){
      return productRepository.findAll(pageable);
    }
		Specification<Product> spec = specBuilder.getSpecificationFromFilters(filters);
		return productRepository.findAll(spec, pageable);
	}

  @Override
  public Product update(Long id, ProductUpdateRequest request) throws ResourceNotFoundException {
    var product = findById(id);
    productMapper.updateEntity(request, product);
    return productRepository.save(product);
  }

  @Override
  public void delete(Long id) throws ResourceNotFoundException {
    productRepository.deleteById(id);
  }
}
