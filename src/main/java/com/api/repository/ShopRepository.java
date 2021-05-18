package com.api.repository;

import com.api.entities.AppUser;
import com.api.entities.Shop;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
  public ArrayList<Shop> findAllByAppUser(AppUser appUser);
}
