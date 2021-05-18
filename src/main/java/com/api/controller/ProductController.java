package com.api.controller;

import com.api.response.ProductResponse;
import com.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductController {
  @Autowired private ProductService productService;

  @PostMapping("/products")
  public ResponseEntity saveProduct(@RequestBody ProductResponse productResponse) {
    try {
      this.productService.addProduct(productResponse);
      return new ResponseEntity(null, HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @GetMapping("/products/{shopId}")
  public ResponseEntity getProducts(@PathVariable Long shopId) {
    try {
      return new ResponseEntity(this.productService.getAll(shopId), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PostMapping("/products/{productId}")
  public ResponseEntity deleteProduct(@PathVariable Long productId, @RequestBody Long id) {
    try {
      this.productService.deleteProduct(productId);
      return new ResponseEntity(null, HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }

  @PostMapping("/products/all/{shopId}")
  public ResponseEntity deleteAllProducts(@PathVariable Long shopId, @RequestBody Long id) {
    try {
      this.productService.deleteShopList(shopId);
      return new ResponseEntity(null, HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }
}
