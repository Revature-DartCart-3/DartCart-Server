package com.revature.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class hard codes data of an individual Product placed on an invoice.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_detail_id")
  private int id;

  @Column(updatable = false)
  private int cost;

  @Column(updatable = false)
  private String name;

  @Column(columnDefinition = "text", updatable = false)
  private String description;

  private int quantity;
}
