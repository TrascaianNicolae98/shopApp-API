package com.api.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String quantity;

  @ManyToOne(targetEntity = Shop.class)
  @JoinColumn(name = "shop_id")
  private Shop shop;

  public Product(String name, String quantity, Shop shop) {
    this.name = name;
    this.quantity = quantity;
    this.shop = shop;
  }
}
