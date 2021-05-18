package com.api.response;

import lombok.Data;

@Data
public class ProductResponse {
  private String name;
  private String quantity;
  private Long shopId;
}
