package com.revature.unit.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.revature.models.Category;
import com.revature.models.Product;
import com.revature.repositories.ProductRepo;
import com.revature.services.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = com.revature.driver.DartCartApplication.class)
public class ProductServicesTests {
  @Autowired
  ProductService productService;

  @MockBean
  private ProductRepo productRepository;

  @Test
  public void testGetProductById() {
    List<Category> categories = new ArrayList<>();
    categories.add(new Category(1, "Food"));
    Product p = new Product(
      1,
      "Kelloggs Froot Loops",
      "Delicious frooty flava",
      "",
      categories
    );
    when(productRepository.findById(1)).thenReturn(Optional.of(p));

    Optional<Product> productOptional = productService.getProductById(1);
    Product product = productOptional.orElse(new Product());

    assertEquals(1, product.getId());
    assertNotEquals(0, product.getId());
    assertEquals("Kelloggs Froot Loops", product.getName());
    assertNotEquals("Kelloggs Frosted Flakes", product.getName());
    assertEquals("Delicious frooty flava", product.getDescription());
    assertNotEquals("Disgusting frooty flava", product.getDescription());

    assertEquals(p.getId(), product.getId());
    assertNotEquals(0, product.getId());
    assertEquals(p.getDescription(), product.getDescription());
    assertNotEquals("Disgusting frooty flava", product.getDescription());
    assertEquals(p.getCategories().get(0), product.getCategories().get(0));
    assertNotEquals("Not Food", product.getCategories().get(0));
  }
}
