package com.api.service;

import com.api.dto.ShopDto;
import com.api.entities.Shop;
import com.api.repository.ShopRepository;
import com.api.response.ShopResponse;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
  @Autowired private ShopRepository shopRepository;
  @Autowired private AppUserService appUserService;
  @Autowired private ProductService productService;

  public void addShop(ShopResponse shopResponse) {
    this.shopRepository.save(
        new Shop(shopResponse.getName(), this.appUserService.findById(shopResponse.getUserId())));
  }

  public Shop getShopById(Long id) {
    return this.shopRepository.findById(id).get();
  }

  public void deleteShop(Long id) {
    this.productService.deleteShopList(id);
    this.shopRepository.delete(this.shopRepository.findById(id).get());
  }

  private ShopDto convertToDto(Shop shop) {
    return new ShopDto(shop.getId(), shop.getName());
  }

  private ArrayList<ShopDto> convertToDtoList(Long userId) {
    ArrayList<ShopDto> shopDtos = new ArrayList<>();
    for (Shop shop : this.shopRepository.findAllByAppUser(this.appUserService.findById(userId))) {
      shopDtos.add(this.convertToDto(shop));
    }
    return shopDtos;
  }

  public ArrayList<ShopDto> getAll(Long userId) {
    return this.convertToDtoList(userId);
  }
}
