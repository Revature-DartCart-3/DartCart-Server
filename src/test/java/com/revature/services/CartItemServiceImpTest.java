package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.driver.DartCartApplication;
import com.revature.models.CartItem;
import com.revature.models.ShopProduct;
import com.revature.models.User;
import com.revature.repositories.CartItemRepo;

@SpringBootTest(classes = DartCartApplication.class)
public class CartItemServiceImpTest {

	@MockBean
	private CartItemRepo mockCartItemRepo;
	
	@MockBean
	private UserServiceImpl mockUserService;
	
	@MockBean
	private ShopProductServiceImpl mockShopProductService;
	
	@Autowired
	private CartItemServiceImp sut;
	                                   //id, quantity, wishList, user, ShopProduct
	private CartItem testCartItem = new CartItem(1, 1, false, new User(), new ShopProduct());
	
	@Test
	public void test_getAllCartItem() {
		List<CartItem> testList = new ArrayList<>();
		when(mockCartItemRepo.findAll()).thenReturn(testList);
		assertEquals(testList, sut.getAllCartItem());
	}
	
	@Test
	public void test_getAllCartItem_byUserId() {
		List<CartItem> testList = new ArrayList<>();
		when(mockCartItemRepo.getAllCartItem(2)).thenReturn(testList);
		assertEquals(testList, sut.getAllCartItem(2));
	}
	
	@Test
	public void test_addCartItem_existingCartItem() {
		CartItem addCartItem = new CartItem(10, 1, false, new User(4), new ShopProduct(5, 2, 4,8, null, null));
		when(mockCartItemRepo.getByShopProductId(5, 4)).thenReturn(addCartItem);
		when(mockCartItemRepo.save(addCartItem)).thenReturn(addCartItem);
		int cartNum = sut.addCartItem(addCartItem).getQuantity();
		assertEquals(2, cartNum);
	}
	
	@Test
	public void test_addCartItem_nonexistingCartItem() {
		ShopProduct shopProd = new ShopProduct(5, 2, 4,8, null, null);
		Optional<ShopProduct> oshopProd = Optional.ofNullable(shopProd);
		
		User testUser = new User(4);
		Optional<User> otestUser = Optional.ofNullable(testUser);
		
		CartItem adCartItem = new CartItem(10, 1, false, testUser, shopProd);
		
		when(mockCartItemRepo.getByShopProductId(5, 4)).thenReturn(null);
		when(mockShopProductService.getShopProductById(5)).thenReturn(oshopProd);
		when(mockUserService.getUserById(4)).thenReturn(otestUser);
		when(mockCartItemRepo.save(new CartItem(0, 1, false, testUser, shopProd))).thenReturn(adCartItem);
		
		assertEquals(adCartItem, sut.addCartItem(adCartItem));
	}
	
	@Test
	public void test_updateCartItem() {
		when(mockCartItemRepo.save(testCartItem)).thenReturn(testCartItem);
		assertEquals(testCartItem, sut.updateCartItem(testCartItem));
	}
	
	@Test
	public void test_getCurrentCartbyId() {
		when(mockCartItemRepo.getCurrentCart(1)).thenReturn(testCartItem);
		assertEquals(testCartItem, sut.getCurrentCartbyId(1));
	}
	
	@Test
	public void test_getbyId() {
		Optional<CartItem> otestCartItem = Optional.ofNullable(testCartItem);
		when(mockCartItemRepo.findById(1)).thenReturn(otestCartItem);
		assertEquals(testCartItem, sut.getbyId(1));
	}
	
	@Test
	public void test_deleteById() {
		assertTrue(sut.deleteById(1));
		doThrow(IllegalArgumentException.class).when(mockCartItemRepo).deleteById(2);
		assertFalse(sut.deleteById(2));
	}
	
}
