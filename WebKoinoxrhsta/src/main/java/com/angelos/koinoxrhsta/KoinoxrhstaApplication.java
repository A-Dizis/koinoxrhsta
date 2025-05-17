package com.angelos.koinoxrhsta;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class KoinoxrhstaApplication {
  public static void main(String[] args) throws Exception {

    new SpringApplicationBuilder()
      .sources(CoreModuleApplication.class)
      .run(args);
  }
}