package com.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class AppException extends HttpServerErrorException {
  public AppException(String message, HttpStatus status) {
    super(status, message);
  }
}
