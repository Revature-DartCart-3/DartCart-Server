package com.revature.services;

import com.revature.driver.DartCartApplication;
import com.revature.models.*;
import com.revature.repositories.ShopProductRepo;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = DartCartApplication.class)
public class ShopProductServiceTest {
  private final Seller mockSeller = new Seller(
    1,
    "name",
    "test.com",
    "desc",
    null
  );
  private final Product mockProduct = new Product(1, "apple", "appley", null);

  private final Shop mockShop = new Shop(1, "Here", mockSeller);
  private final ShopProduct mockShopProduct = new ShopProduct(
    1,
    1,
    2,
    0,
    mockShop,
    mockProduct
  );

  @Autowired
  private WebApplicationContext webApplicationContext;

  @MockBean
  private ShopProductRepo mockShopProductRepo;

  @Autowired
  private ShopProductServiceImpl mockShopService;

  @BeforeEach
  void setup() {
    MockMvc mvc = MockMvcBuilders
      .webAppContextSetup(webApplicationContext)
      .build();
  }

  @Test
  void getAllSellersForProductTest() {
    ArrayList<ShopProduct> testList = new ArrayList<>();
    testList.add(mockShopProduct);
    Mockito
      .when(mockShopProductRepo.findByProduct(mockShopProduct.getProduct()))
      .thenReturn(testList);
    Mockito
      .when(mockShopProductRepo.findById(mockShopProduct.getId()))
      .thenReturn(Optional.of(mockShopProduct));

    Assertions.assertEquals(
      new ShopProductResponse(
        mockShopProduct.getId(),
        mockShop,
        mockProduct,
        mockShopProduct.getPrice(),
        mockShop.getLocation(),
        mockShopProduct.getDiscount(),
        mockShopProduct.getQuantity(),
        mockSeller.getDescription()
      ),
      mockShopService.getSellersForProduct(mockShopProduct.getId()).get(0)
    );
  }
}
