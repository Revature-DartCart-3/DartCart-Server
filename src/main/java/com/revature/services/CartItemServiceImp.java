package com.revature.services;

import com.revature.models.CartItem;
import com.revature.models.ShopProduct;
import com.revature.repositories.CartItemRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImp implements CartItemService {
	
  @Autowired
  CartItemRepo cir;

  @Autowired
  UserService userService;

  @Autowired
  ShopProductService shopProductService;

  @Override
  public List<CartItem> getAllCartItem() {
    return (List<CartItem>) cir.findAll();
  }

  @Override
  public List<CartItem> getAllCartItem(int userId) {
    return cir.getAllCartItem(userId);
  }

  @Override
  public CartItem addCartItem(CartItem cartItem) {
    CartItem tempCartItem = cir.getByShopProductId(
      cartItem.getShopProduct().getId(),
      cartItem.getCustomer().getId()
    );

    if (tempCartItem != null) {
      tempCartItem.setQuantity(tempCartItem.getQuantity() + 1);
      return cir.save(tempCartItem);
    } else {
      ShopProduct shopProduct = shopProductService
        .getShopProductById(cartItem.getShopProduct().getId())
        .orElse(null);
      shopProduct.getId();
      CartItem item = new CartItem(
        cartItem.getQuantity(),
        cartItem.isSaved(),
        userService.getUserById(cartItem.getCustomer().getId()).orElse(null),
        shopProduct
      );

      return cir.save(item);
    }
  }

  @Override
  public CartItem updateCartItem(CartItem change) {
    return cir.save(change);
  }

  @Override
  public CartItem getCurrentCartbyId(int userId) {
    return cir.getCurrentCart(userId);
  }

  @Override
  public CartItem getbyId(int id) {
    return cir.findById(id).orElse(null);
  }

  @Override
  public boolean deleteById(int id) {
    try {
      cir.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
}
