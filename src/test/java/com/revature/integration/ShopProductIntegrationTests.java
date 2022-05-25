//package com.revature.integration;
//
//import com.revature.models.ShopProduct;
//import com.revature.repositories.ShopProductRepo;
//import java.util.List;
//import java.util.Optional;
//import javax.transaction.Transactional;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest(classes = com.revature.driver.DartCartApplication.class)
//@Transactional
//public class ShopProductIntegrationTests {
//  @Autowired
//  private ShopProductRepo shopProductRepository;
//
//  @Test
//  void getAllShopProducts() {
//    List<ShopProduct> allShopProducts = (List<ShopProduct>) shopProductRepository.findAll();
//    Assertions.assertNotNull(allShopProducts);
//    Assertions.assertEquals(2, allShopProducts.size());
//  }
//
//  @Test
//  void getShopProductById() {
//    Optional<ShopProduct> shopProduct = shopProductRepository.findById(1);
//    Assertions.assertEquals(
//      "Kelloggs Froot Loops",
//      shopProduct.get().getProduct().getName()
//    );
//    Assertions.assertEquals(1, shopProduct.get().getProduct().getId());
//    Assertions.assertEquals(10, shopProduct.get().getQuantity());
//    Assertions.assertEquals(15, shopProduct.get().getPrice());
//  }
//}
