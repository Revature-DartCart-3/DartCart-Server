package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.models.Seller;
import com.sun.istack.NotNull;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shops")
public class Shop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "shop_id")
  private int id;

  @NotNull
  private String location;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "seller_id")
  private Seller seller;

  public Shop(int id) {
    this.id = id;
    location = "";
    seller = new Seller();
  }
}
