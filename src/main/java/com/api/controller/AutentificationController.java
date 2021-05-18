package com.api.controller;

import com.api.dto.LoginDto;
import com.api.service.AuthenticationService;
import com.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AutentificationController {
  @Autowired AuthenticationService authenticationService;
  @Autowired private JwtUtil jwtUtil;

  @PostMapping("/loginWithGoogle")
  public ResponseEntity loginWithGoogle(@RequestBody String token) throws Exception {
    try {
      String jwt = this.authenticationService.createJwt(token);
      Long id = jwtUtil.extractId(jwtUtil.extractAllClaims(jwt));
      return new ResponseEntity(new LoginDto(jwt, id), HttpStatus.OK);
    } catch (HttpServerErrorException e) {
      return new ResponseEntity(e.getMessage(), e.getStatusCode());
    }
  }
}
