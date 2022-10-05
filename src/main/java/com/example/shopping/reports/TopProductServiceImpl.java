package com.example.shopping.reports;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.order.entities.LineItem;
import com.example.shopping.order.entities.LineItem_;
import com.example.shopping.product.entities.Category;
import com.example.shopping.product.entities.Category_;
import com.example.shopping.product.entities.Product;
import com.example.shopping.product.entities.Product_;
import com.example.shopping.reports.dto.TopProductsResponse;

@Service
public class TopProductServiceImpl implements TopProductService {

  @Autowired
  private EntityManager entityManager;

  @Override
  public List<TopProductsResponse> get() {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(TopProductsResponse.class);

    Root<Product> product = criteriaQuery.from(Product.class);
    Join<Product, Category> category = product.join(Product_.category, JoinType.INNER);
    Join<Product, LineItem> orders = product.join(Product_.orderItems, JoinType.LEFT);

    criteriaQuery.multiselect(product.get(Product_.id), 
                              product.get(Product_.name), 
                              category.get(Category_.name),
                              criteriaBuilder.sum(orders.get(LineItem_.quantity)));
    criteriaQuery.groupBy(product.get(Product_.id), 
                          product.get(Product_.name), 
                          category.get(Category_.name));

    criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.sum(orders.get(LineItem_.quantity))));

    var query = entityManager.createQuery(criteriaQuery);
    query.setMaxResults(5);
    var response = query.getResultList();
    return response;
  }

}
