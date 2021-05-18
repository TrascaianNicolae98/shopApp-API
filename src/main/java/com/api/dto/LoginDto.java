package com.api.dto;

import lombok.Data;

@Data
public class LoginDto {
  private String jwt;
  private Long userId;

  public LoginDto(String jwt, Long userId) {
    this.jwt = jwt;
    this.userId = userId;
  }
}
