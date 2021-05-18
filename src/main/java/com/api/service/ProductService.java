package com.api.service;

import com.api.dto.ProductDto;
import com.api.dto.ProductsDto;
import com.api.entities.Product;
import com.api.repository.ProductRepository;
import com.api.response.ProductResponse;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired private ProductRepository productRepository;
  @Autowired private ShopService shopService;

  public void addProduct(ProductResponse productResponse) {
    this.productRepository.save(
        new Product(
            productResponse.getName(),
            productResponse.getQuantity(),
            this.shopService.getShopById(productResponse.getShopId())));
  }

  public void deleteProduct(Long id) {
    this.productRepository.deleteById(id);
  }

  public void deleteShopList(Long id) {
    this.productRepository.deleteAllByShop(id);
  }

  private ProductDto convertToDto(Product product) {
    return new ProductDto(product.getId(), product.getName(), product.getQuantity());
  }

  private ArrayList<ProductDto> convertToDtoList(Long shopId) {
    ArrayList<ProductDto> productDtos = new ArrayList<>();
    for (Product product :
        this.productRepository.findAllByShop(this.shopService.getShopById(shopId))) {
      productDtos.add(this.convertToDto(product));
    }
    return productDtos;
  }

  public ProductsDto getAll(Long shopId) {
    return new ProductsDto(
        this.shopService.getShopById(shopId).getName(), this.convertToDtoList(shopId));
  }
}
