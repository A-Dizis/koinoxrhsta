package com.angelos.koinoxrhsta;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.angelos.koinoxrhsta.impl.config.CoreEntityConfiguration;
import com.angelos.koinoxrhsta.impl.config.CoreMapperConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class KoinoxrhstaApplication {
  public static void main(String[] args) throws Exception {
    new CoreEntityConfiguration();
    new CoreMapperConfiguration();

    new SpringApplicationBuilder()
      .sources(KoinoxrhstaApplication.class)
      .run(args);
  }
}