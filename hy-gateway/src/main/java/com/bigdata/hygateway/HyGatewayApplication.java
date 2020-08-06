package com.bigdata.hygateway;

import com.bigdata.hygateway.resolver.HostAddrKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class HyGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(HyGatewayApplication.class, args);
  }

  @Bean(name = "hostAddrKeyResolver")
  public HostAddrKeyResolver hostAddrKeyResolver() {
    return new HostAddrKeyResolver();
  }
}
