package com.api.controller;

import com.api.response.ShopResponse;
import com.api.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ShopController {
  @Autowired private ShopService shopService;

  @PostMapping("/shops")
  public ResponseEntity saveShop(@RequestBody ShopResponse shopResponse) {
    try {
      this.shopService.addShop(shopResponse);
      return new ResponseEntity(null, HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PostMapping("/shops/{shopId}")
  public ResponseEntity deleteShop(@PathVariable Long shopId, @RequestBody Long id) {
    try {
      this.shopService.deleteShop(shopId);
      return new ResponseEntity(null, HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @GetMapping("/shops/{userId}")
  public ResponseEntity getShops(@PathVariable Long userId) {
    try {
      return new ResponseEntity(this.shopService.getAll(userId), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }
}
