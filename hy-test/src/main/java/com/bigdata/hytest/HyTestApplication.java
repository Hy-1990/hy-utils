package com.bigdata.hytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class HyTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(HyTestApplication.class, args);
  }
}
