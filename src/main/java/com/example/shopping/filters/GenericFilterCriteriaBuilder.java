package com.example.shopping.filters;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.shopping.utils.CommonUtils;

import static org.springframework.data.jpa.domain.Specification.where;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenericFilterCriteriaBuilder {

    public <T> Specification<T> getSpecificationFromFilters(List<FilterCondition> filters) {
        Specification<T> specification = where(createSpecification(filters.remove(0)));
        for (var input : filters) {
            specification = specification.and(createSpecification(input));
        }
        return specification;
    }

    private <T> Specification<T> createSpecification(FilterCondition input) {
        switch (input.getOperator()){
            case EQUALS:
            return (root, query, criteriaBuilder) -> 
               criteriaBuilder.equal(root.get(input.getField()),
                castToRequiredType(root.get(input.getField()).getJavaType(), 
                                   input.getValue()));
         
            case NOT_EQUALS:
                return (root, query, criteriaBuilder) -> 
                criteriaBuilder.notEqual(root.get(input.getField()),
                    castToRequiredType(root.get(input.getField()).getJavaType(), 
                                    input.getValue()));
            
            case GREATER_THAN:
                return (root, query, criteriaBuilder) -> 
                criteriaBuilder.gt(root.get(input.getField()),
                    (Number) castToRequiredType(
                        root.get(input.getField()).getJavaType(), 
                                    input.getValue()));
            
            case LESS_THAN:
                return (root, query, criteriaBuilder) -> 
                criteriaBuilder.lt(root.get(input.getField()),
                    (Number) castToRequiredType(
                        root.get(input.getField()).getJavaType(), 
                                    input.getValue()));
            
            case LIKE:
            return (root, query, criteriaBuilder) -> 
                criteriaBuilder.like(root.get(input.getField()), 
                                "%"+input.getValue()+"%");
            
            case IN:
            return (root, query, criteriaBuilder) -> 
                criteriaBuilder.in(root.get(input.getField()))
                .value(castToArray(
                        root.get(input.getField()).getJavaType(), 
                        input.getValue()));
            
            default:
            throw new RuntimeException("Operation not supported yet");
        }
    }
    private Object castToRequiredType(Class fieldType, String value) {
        if(fieldType.isAssignableFrom(Double.class)) {
          return Double.valueOf(value);
        } else if(fieldType.isAssignableFrom(Integer.class)) {
          return Integer.valueOf(value);
        } else if(Enum.class.isAssignableFrom(fieldType)) {
          return Enum.valueOf(fieldType, value);
        }
        return value;
      }
    
    private Object castToArray(Class fieldType, String value) {
        final String ARRAY_DELIMITER = ",";

                List<String> values = CommonUtils.split(value, ARRAY_DELIMITER);
        List<Object> lists = new ArrayList<>();
        for (String s : values) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }
}
