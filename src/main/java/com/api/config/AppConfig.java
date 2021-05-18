package com.api.config;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public JacksonFactory getJasonFactory() {
    return new JacksonFactory();
  }

  @Bean
  public NetHttpTransport getHttpTransport() {
    return new NetHttpTransport();
  }
}
