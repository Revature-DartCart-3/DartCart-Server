//package com.revature.services;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.revature.driver.DartCartApplication;
//import com.revature.models.Invoice;
//import com.revature.models.Shop;
//import com.revature.models.User;
//import com.revature.repositories.InvoiceRepo;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//@SpringBootTest(classes = DartCartApplication.class)
//public class InvoiceServiceTest {
//  @MockBean
//  private InvoiceRepo invoiceRepo;
//
//  @Autowired
//  private InvoiceService invoiceService;
//
//  private static ArrayList<Invoice> all;
//
//  @BeforeAll
//  public static void setUp() {
//    all = new ArrayList<>();
//
//    all.add(
//      new Invoice(1, 0L, "address", "address", new User(1), new Shop(1), null)
//    );
//    all.add(
//      new Invoice(1, 0L, "address", "address", new User(2), new Shop(2), null)
//    );
//    all.add(
//      new Invoice(1, 0L, "address", "address", new User(3), new Shop(3), null)
//    );
//  }
//
//  @Test
//  public void getAllInvoicesBySellerId_WhenPresent() {
//    Mockito.when(invoiceRepo.findAll()).thenReturn(all);
//
//    List<Invoice> ret = invoiceService.getAllInvoicesBySellerId(0);
//
//    assertEquals(3, ret.size());
//  }
//
//  @Test
//  public void getAllInvoicesBySellerId_WhenNotPresent() {
//    Mockito.when(invoiceRepo.findAll()).thenReturn(all);
//
//    List<Invoice> ret = invoiceService.getAllInvoicesBySellerId(1);
//
//    assertEquals(0, ret.size());
//  }
//
//  @Test
//  public void getInvoiceByCustomerId_WhenNotPresent() {
//    Mockito.when(invoiceRepo.findAll()).thenReturn(all);
//
//    List<Invoice> ret = invoiceService.getInvoiceByCustomerId(1);
//
//    assertEquals(1, ret.size());
//  }
//
//  @Test
//  public void getInvoiceByCustomerId_WhenPresent() {
//    Mockito.when(invoiceRepo.findAll()).thenReturn(all);
//
//    List<Invoice> ret = invoiceService.getInvoiceByCustomerId(4);
//
//    assertEquals(0, ret.size());
//  }
//
//  @Test
//  public void getInvoicesByShopId_Test() {
//    Mockito.when(invoiceRepo.findAllByShopId(4)).thenReturn(all);
//
//    List<Invoice> ret = invoiceService.getInvoicesByShopId(4);
//
//    assertNotNull(ret);
//  }
//}
