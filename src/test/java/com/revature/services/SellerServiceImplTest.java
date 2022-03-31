package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.revature.driver.DartCartApplication;
import com.revature.models.Seller;
import com.revature.models.User;
import com.revature.repositories.SellerRepo;

@SpringBootTest(
		  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		  classes = DartCartApplication.class
		)
public class SellerServiceImplTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private SellerRepo mockSellerRepo;
	
	@Autowired
	private SellerServiceImpl sut;
	
	private Seller testSeller = new Seller();
	
	@BeforeEach
	  void setup() {
	    MockMvc mvc = MockMvcBuilders
	      .webAppContextSetup(webApplicationContext)
	      .build();
	  }
	
//	@Before
//	private void sellerSetup() {
//		testSeller = new Seller();
//		testSeller.setId(1);
//		testSeller.setName("testName");
//		testSeller.setHomepage("HomePage");
//		testSeller.setDescription("Description");
//		testSeller.setUser(new User(1));
//	}
	
	
	@Test
	public void test_addSeller() {
		when(mockSellerRepo.save(testSeller)).thenReturn(testSeller);
		assertEquals(testSeller, sut.addSeller(testSeller));
	}
	
	@Test
	public void test_getSellerById() {
		Optional<Seller> oSeller = Optional.ofNullable(testSeller);
		when(mockSellerRepo.findById(1)).thenReturn(oSeller);
		Seller checkSeller = sut.getSellerById(1).get();
		assertEquals(testSeller, checkSeller);
	}
	
	@Test
	public void test_getAllSellers() {
		List<Seller> sellerList = new ArrayList<>();
		sellerList.add(testSeller);
		when(mockSellerRepo.findAll()).thenReturn(sellerList);
		assertEquals(sellerList, sut.getAllSellers());
	}
	
	@Test
	public void test_deleteSeller() {
		assertTrue(sut.deleteSeller(1));
		doThrow(IllegalArgumentException.class).when(mockSellerRepo).deleteById(9);
		assertFalse(sut.deleteSeller(9));
	}
	
	@Test
	public void test_getSellerByUserId() {
		Optional oSeller = Optional.ofNullable(testSeller);
		when(mockSellerRepo.findByUserId(1)).thenReturn(oSeller);
		assertEquals(oSeller, sut.getSellerByUserId(1));
	}
	
}
