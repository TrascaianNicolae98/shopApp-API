package com.api.dto;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductsDto {
  private String shopName;
  private ArrayList<ProductDto> productDtos;
}
