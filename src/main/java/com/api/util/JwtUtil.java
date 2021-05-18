package com.api.util;

import com.api.entities.AppUser;
import com.api.error.AppException;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import javax.xml.bind.DatatypeConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
@Log4j2
public class JwtUtil {
  @Value("${secretKeyJwt}")
  private String secret_key;

  public String extractUsername(Claims claims) {
    return claims.get("name").toString();
  }

  public String extractEmail(Claims claims) {
    return claims.get("email").toString();
  }

  public String extractPhoneNo(Claims claims) {
    return claims.get("phoneNo").toString();
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public Date extractCreationDate(String token) {
    return extractClaim(token, Claims::getIssuedAt);
  }

  public Long extractId(Claims claims) {
    return Long.parseLong(claims.get("id").toString());
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public Boolean isJwtNull(String token) throws AppException {
    if (token != null) {
      return true;
    } else {
      log.error("Jwt is null!");
      throw new AppException("Jwt is null!", HttpStatus.UNAUTHORIZED);
    }
  }

  public Claims extractAllClaims(String token) {
    try {
      return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException
        | UnsupportedJwtException
        | MalformedJwtException
        | SignatureException
        | IllegalArgumentException e) {
      throw new AppException("Invalid Jwt", HttpStatus.UNAUTHORIZED);
    }
  }

  private Boolean isTokenExpired(String token) throws HttpServerErrorException {
    if (extractExpiration(token).before(new Date()) == false) {
      return false;
    } else {
      log.error("Token is expired!");
      throw new AppException("Token is expired!", HttpStatus.UNAUTHORIZED);
    }
  }

  public String createToken(Map<String, Object> claims, String subject) {
    long timeUntillExpiration = 1000 * 60 * 60 * 10; // 10hours
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + timeUntillExpiration))
        .signWith(SignatureAlgorithm.HS384, secret_key)
        .compact();
  }

  public Boolean validateEmail(String email, Claims claims) throws HttpServerErrorException {
    if (this.extractEmail(claims).equals(email)) {
      return true;
    } else {
      log.error("Invalid email!");
      throw new AppException("Invalid email!", HttpStatus.UNAUTHORIZED);
    }
  }

  public Boolean validateUserName(String userName, Claims claims) {
    if (this.extractUsername(claims).equals(userName)) {
      return true;
    } else {
      log.error("Invalid userName!");
      throw new AppException("Invalid userName!", HttpStatus.UNAUTHORIZED);
    }
  }

  private Boolean validateClaims(Claims claims, AppUser appUser) throws AppException {
    Boolean validateEmail = this.validateEmail(appUser.getEmail(), claims);
    Boolean validateUsername = this.validateUserName(appUser.getFullName(), claims);
    return true;
  }

  public Boolean validateToken(String token, AppUser appUser) throws HttpServerErrorException {
    try {
      Claims claims =
          Jwts.parser()
              .setSigningKey(DatatypeConverter.parseBase64Binary(secret_key))
              .parseClaimsJws(token)
              .getBody();
      validateClaims(claims, appUser);
      return true;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new AppException(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
  }
}
