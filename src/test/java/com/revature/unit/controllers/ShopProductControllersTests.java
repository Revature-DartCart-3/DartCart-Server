package com.revature.unit.controllers;

import com.revature.driver.DartCartApplication;
import com.revature.models.Category;
import com.revature.models.Product;
import com.revature.models.Shop;
import com.revature.models.ShopProduct;
import com.revature.services.ShopProductServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = DartCartApplication.class
)
public class ShopProductControllersTests {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @MockBean
  private ShopProductServiceImpl sps;

  @BeforeEach
  void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  private static final Shop shop = new Shop();

  static final ShopProduct testShopProduct = new ShopProduct(
    1,
    20,
    50,
    0,
    shop,
    new Product(
      1,
      "testProduct",
      "testDescription",
      "",
      new ArrayList<Category>(Arrays.asList(new Category(1, "testCategory")))
    )
  );

  static final ShopProduct testShopProduct2 = new ShopProduct(
    1,
    30,
    70,
    2,
    shop,
    new Product(
      2,
      "testProduct2",
      "testDescription2",
      "",
      new ArrayList<Category>(Arrays.asList(new Category(2, "testCategory2")))
    )
  );

  static final ShopProduct testShopProduct3 = new ShopProduct(
    1,
    90,
    10,
    5,
    shop,
    new Product(
      3,
      "testProduct3",
      "testDescription3",
      "",
      new ArrayList<Category>(Arrays.asList(new Category(3, "testCategory3")))
    )
  );

  @Test
  void getShopProductByIdPass() throws Exception {
    Mockito
      .when(sps.getShopProductById(1))
      .thenReturn(Optional.of(testShopProduct));
    ResultActions ra = mvc.perform(
      MockMvcRequestBuilders.get("/shop_products/1")
    );
    ra.andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void getShopProductByIdFail() throws Exception {
    Mockito.when(sps.getShopProductById(2)).thenReturn(Optional.empty());
    ResultActions ra = mvc.perform(
      MockMvcRequestBuilders.get("/shop_products/2")
    );
    ra.andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void getAllShopProducts() throws Exception {
    List<ShopProduct> testList = new ArrayList<>();
    testList.add(testShopProduct);
    testList.add(testShopProduct2);
    testList.add(testShopProduct3);

    Mockito.when(sps.getAllShopProducts()).thenReturn(testList);
    ResultActions ra = mvc.perform(
      MockMvcRequestBuilders.get("/shop_products")
    );
    ra.andExpect(MockMvcResultMatchers.status().isOk());
    ra.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(testShopProduct));
    ra.andExpect(
      MockMvcResultMatchers.jsonPath("$[1]").value(testShopProduct2)
    );
    ra.andExpect(
      MockMvcResultMatchers.jsonPath("$[2]").value(testShopProduct3)
    );
  }

  // This test needs work
  @Test
  void searchProductByCategory() throws Exception {
    List<Product> products = new ArrayList<>();
    Category category = new Category(1, "Food");
    List<Category> categories = new ArrayList<>();
    categories.add(category);

    Product product = new Product(
      1,
      "Kelloggs Froot Loops",
      "Delicious frooty flava",
      "",
      categories
    );
    products.add(product);

    Mockito.when(sps.getByProductCategory(null, "Food")).thenReturn(products);
    ResultActions resultActions = mvc.perform(
      MockMvcRequestBuilders.get("/shop_products/search?category=Food")
    );
    resultActions.andExpectAll(
      (MockMvcResultMatchers.jsonPath("$[0]").value(product)),
      (MockMvcResultMatchers.status().isOk())
    );
  }
}
