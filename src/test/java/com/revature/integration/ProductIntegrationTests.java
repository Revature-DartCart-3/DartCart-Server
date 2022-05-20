//package com.revature.integration;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import com.revature.models.Product;
//import com.revature.repositories.ProductRepo;
//import java.util.Optional;
//import javax.transaction.Transactional;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//
//@SpringBootTest(classes = com.revature.driver.DartCartApplication.class)
//@Transactional
//public class ProductIntegrationTests {
//  @Autowired
//  private ProductRepo pr;
//
//  @Test
//  public void getProductById() {
//    Optional<Product> product = pr.findById(1);
//
//    assertTrue(product.isPresent());
//
//    Product p = product.get();
//
//    assertEquals(p.getId(), 1);
//    assertEquals(p.getName(), "Kelloggs Froot Loops");
//    assertEquals(p.getDescription(), "Delicious frooty flava");
//    assertEquals(p.getCategories().size(), 1);
//  }
//}
