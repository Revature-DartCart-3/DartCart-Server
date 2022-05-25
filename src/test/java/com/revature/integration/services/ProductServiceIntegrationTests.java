//package com.revature.integration.services;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.revature.models.Product;
//import com.revature.services.ProductServiceImpl;
//import java.util.Optional;
//import javax.transaction.Transactional;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest(classes = com.revature.driver.DartCartApplication.class)
//@Transactional
//public class ProductServiceIntegrationTests {
//  @Autowired
//  private ProductServiceImpl ps;
//
//  @Test
//  public void testGetProductById() {
//    Optional<Product> p = ps.getProductById(1);
//    assertTrue(p.isPresent());
//
//    assertEquals(p.get().getId(), 1);
//    assertNotEquals(p.get().getId(), 5);
//
//    assertEquals(p.get().getName(), "Kelloggs Froot Loops");
//    assertNotEquals(p.get().getName(), "failData");
//
//    assertEquals(p.get().getDescription(), "Delicious frooty flava");
//    assertNotEquals(p.get().getDescription(), "failData");
//  }
//}
