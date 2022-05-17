package com.revature.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shop_products")
public class ShopProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "shop_product_id")
  private int id;

  private int quantity;
  private int price;
  private int discount;

  @ManyToOne
  @JoinColumn(name = "shop_id")
  private Shop shop;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;
}
