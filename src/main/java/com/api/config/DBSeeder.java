package com.api.config;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DBSeeder {

  public DBSeeder() {}

  @PostConstruct
  public void seedConnection() {}
}
