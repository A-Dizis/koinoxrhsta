package com.angelos.koinoxrhsta;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class KoinoxrhstaApplication {
  public static void main(String[] args) throws Exception {
    new SpringApplicationBuilder()
      .sources(KoinoxrhstaApplication.class)
      .run(args);
  }
}