package com.example.shopping.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.shopping.product.services.CategoryServiceImpl;
import com.example.shopping.product.repositories.CategoryRepository;
import com.example.shopping.exception.DuplicatedException;
import com.example.shopping.product.entities.Category;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {
  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private CategoryServiceImpl categoryService;

  private Category category;

  @BeforeEach
  public void setup() {
    category = Category.builder()
        .id(1L)
        .name("Ramesh")
        .build();
  }

  @DisplayName("JUnit test for saveCategory method")
  @Test
  public void givenCategoryObject_whenSaveCategory_thenReturnCategoryObject(){
    // Arrange
    when(categoryRepository.findByName(category.getName())).thenReturn(new ArrayList<Category>());

    // Act
    categoryService.create(category);

    // Assert
    verify(categoryRepository, times(1)).save(category);
  }

  // JUnit test for saveCategory method
  @DisplayName("JUnit test for saveCategory method which throws exception")
  @Test
  public void givenExistingName_whenSaveCategory_thenThrowsException(){

    // Arrange
    when(categoryRepository.findByName(category.getName())).thenReturn(List.of(category));

    // Act
    org.junit.jupiter.api.Assertions.assertThrows(DuplicatedException.class, () -> {
      categoryService.create(category);
    });

    // Assert
    verify(categoryRepository, never()).save(any(Category.class));
  }

  // JUnit test for getAllCategory method
  @DisplayName("JUnit test for getAllCategory method")
  @Test
  public void givenCategoryList_whenFindAll_thenReturnCategoryList() {
    Category category1 = Category.builder()
        .id(2L)
        .name("Tony")
        .build();

    when(categoryRepository.findAll()).thenReturn(List.of(category, category1));

    List<Category> CategoryList = categoryService.findAll();

    assertNotNull(CategoryList);
    assertEquals(CategoryList.size(), 2);
  }
}
