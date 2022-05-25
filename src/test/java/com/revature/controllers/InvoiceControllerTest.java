//package com.revature.controllers;
//
//import com.revature.driver.DartCartApplication;
//import com.revature.services.InvoiceService;
//import java.util.ArrayList;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//@SpringBootTest(
//  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//  classes = DartCartApplication.class
//)
//public class InvoiceControllerTest {
//  private MockMvc mvc;
//
//  @Autowired
//  private WebApplicationContext webApplicationContext;
//
//  @MockBean
//  private InvoiceService mockInvoiceService;
//
//  @BeforeEach
//  void setup() {
//    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//  }
//
//  @Test
//  void getInvoiceByUsername_Test() throws Exception {
//    Mockito
//      .when(mockInvoiceService.getInvoiceByCustomerId(1))
//      .thenReturn(new ArrayList<>());
//
//    mvc
//      .perform(MockMvcRequestBuilders.get("/invoices/customer/1"))
//      .andExpect(MockMvcResultMatchers.status().isOk());
//  }
//
//  @Test
//  void getAllInvoicesBySellerId_Test() throws Exception {
//    Mockito
//      .when(mockInvoiceService.getAllInvoicesBySellerId(1))
//      .thenReturn(new ArrayList<>());
//
//    mvc
//      .perform(MockMvcRequestBuilders.get("/invoices/seller/1"))
//      .andExpect(MockMvcResultMatchers.status().isOk());
//  }
//
//  @Test
//  void getAllInvoicesByShopId_Test() throws Exception {
//    Mockito
//      .when(mockInvoiceService.getInvoicesByShopId(1))
//      .thenReturn(new ArrayList<>());
//
//    mvc
//      .perform(MockMvcRequestBuilders.get("/invoices/shop/1"))
//      .andExpect(MockMvcResultMatchers.status().isOk());
//  }
//}
