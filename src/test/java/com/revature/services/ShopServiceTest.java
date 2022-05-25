//package com.revature.services;
//
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.verify;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.revature.driver.DartCartApplication;
//import com.revature.models.Seller;
//import com.revature.models.Shop;
//import com.revature.repositories.ShopRepo;
//
//@SpringBootTest(classes = DartCartApplication.class)
//public class ShopServiceTest {
//
//	private final Shop testShop = new Shop(1, "Location", new Seller());
//
//	@MockBean
//	private ShopRepo mockShopRepo;
//
//	@Autowired
//	private ShopServiceImpl sut;
//
//	@Test
//	public void test_addShop_validShop() {
//		Mockito.when(mockShopRepo.save(testShop)).thenReturn(testShop);
//		Assertions.assertEquals(testShop, sut.addShop(testShop));
//	}
//
//	@Test
//	public void test_getShopById() {
//		int idExists = 1;
//		int idnotExists = 666;
//		Optional<Shop> oShop = Optional.of(testShop);
//		Optional<Shop> emptyShop = Optional.empty();
//
//		Mockito.when(mockShopRepo.findById(idExists)).thenReturn(oShop);
//		Mockito.when(mockShopRepo.findById(idnotExists)).thenReturn(emptyShop);
//
//		Assertions.assertEquals(oShop, sut.getShopById(idExists));          // If ID exists
//		Assertions.assertEquals(emptyShop, sut.getShopById(idnotExists));   // If ID does not exist
//	}
//
//	@Test
//	public void test_getAllShops() {
//		List<Shop> shopList = new ArrayList<>();
//		shopList.add(testShop);
//		Mockito.when(mockShopRepo.findAll()).thenReturn(shopList);
//		Assertions.assertEquals(shopList, sut.getAllShops());
//	}
//
//	@Test
//	public void test_deleteShop_validId() {
//		boolean deleteTest = sut.deleteShop(1);
//		verify(mockShopRepo).deleteById(1);
//		Assertions.assertTrue(deleteTest);
//	}
//
//	@Test
//	public void test_deleteShop_invalidId() {
//		int nullId = -1;
//		Mockito.doThrow(new IllegalArgumentException()).when(mockShopRepo).deleteById(nullId);
//		Assertions.assertFalse(sut.deleteShop(nullId));
//	}
//
//	@Test
//	public void test_getByUserId_validId() {
//		int userId = 5;
//		List<Shop> shopList = new ArrayList<>();
//		Mockito.when(mockShopRepo.getByUserId(userId)).thenReturn(shopList);
//		Assertions.assertEquals(shopList, sut.getShopsByUserId(userId));
//	}
//
//}