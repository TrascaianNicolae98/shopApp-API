package com.api.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Shop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(targetEntity = AppUser.class)
  @JoinColumn(name = "appUser_id")
  private AppUser appUser;

  public Shop(String name, AppUser appUser) {
    this.name = name;
    this.appUser = appUser;
  }
}
