package com.revature.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopProductResponse {
  private int shop_product_id;
  private Shop shop;
  private Product product;
  private int price;
  private String location;
  private int discount;
  private int quantity;
  private String sellerDescription;
}
