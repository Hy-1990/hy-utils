package com.bigdata.hyzipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class HyZipkinApplication {

  public static void main(String[] args) {
    SpringApplication.run(HyZipkinApplication.class, args);
  }
}
