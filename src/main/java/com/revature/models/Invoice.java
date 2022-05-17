package com.revature.models;

import com.revature.models.User;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "invoice_id")
  private int id;

  @Column(updatable = false)
  private long orderPlaced;

  @Column(length = 1000, updatable = false)
  private String shippedFrom;

  @Column(length = 1000, updatable = false)
  private String shippedTo;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User customer;

  @ManyToOne
  @JoinColumn(name = "shop_id")
  private Shop shop;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "invoice_id")
  private List<OrderDetail> orderDetails;
}
