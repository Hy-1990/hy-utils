package com.bigdata.hytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
@MapperScan(basePackages = {"com.bigdata.hytest.dao.test", "com.bigdata.hytest.dao.test1"})
public class HyTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(HyTestApplication.class, args);
  }
}
