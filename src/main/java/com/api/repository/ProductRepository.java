package com.api.repository;

import com.api.entities.Product;
import com.api.entities.Shop;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
  @Transactional
  @Modifying
  @Query("delete from Product p where p.shop.id=:id")
  public void deleteAllByShop(Long id);

  public ArrayList<Product> findAllByShop(Shop shop);
}
