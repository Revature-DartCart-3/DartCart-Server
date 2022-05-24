package com.revature.models;

import com.sun.istack.NotNull;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a Seller entity in the database.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sellers")
public class Seller {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "seller_id")
  private int id;

  @NotNull
  private String name;

  @NotNull
  @Column(unique = true)
  private String homepage;
  
  @Column(columnDefinition = "text")
  private String description;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
}
