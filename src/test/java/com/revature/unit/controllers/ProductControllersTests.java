package com.revature.unit.controllers;

import com.revature.driver.DartCartApplication;
import com.revature.models.Category;
import com.revature.models.Product;
import com.revature.services.ProductServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
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
public class ProductControllersTests {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @MockBean
  private ProductServiceImpl ps;

  @BeforeEach
  void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  static final Product testProduct = new Product(
    1,
    "testProduct",
    "testDescription",
    "",
    new ArrayList<Category>(Arrays.asList(new Category(1, "testCategory")))
  );

  @Test
  public void testGetProductByIdSuccess() throws Exception {
    Mockito.when(ps.getProductById(1)).thenReturn(Optional.of(testProduct));
    ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/products/1"));
    ra.andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void testGetProductByIdFail() throws Exception {
    Mockito.when(ps.getProductById(3)).thenReturn(Optional.empty());
    ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/products/3"));
    ra.andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}
