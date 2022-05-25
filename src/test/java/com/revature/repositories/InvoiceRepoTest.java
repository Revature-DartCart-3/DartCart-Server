package com.revature.repositories;

import static org.junit.jupiter.api.Assertions.*;

import com.revature.driver.DartCartApplication;
import com.revature.models.Invoice;
import com.revature.models.Seller;
import com.revature.services.InvoiceService;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DartCartApplication.class)
public class InvoiceRepoTest {
 @Autowired
 InvoiceRepo invoiceRepo;

 @ParameterizedTest
 @ValueSource(ints = { 1, 2 })
 public void findAllByShopId_WhenPresent(int id) {
   Iterable<Invoice> output = invoiceRepo.findAllByShopId(id);
   assertNotNull(output);

   for (Invoice elem : output) {
     assertEquals(id, elem.getShop().getId());
   }
 }

 @ParameterizedTest
 @ValueSource(ints = { 9000, 9001 })
 public void findAllByShopId_WhenAbsent(int id) {
   Iterable<Invoice> output = invoiceRepo.findAllByShopId(id);
   assertFalse(output.iterator().hasNext());
 }
}
