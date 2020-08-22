package com.bigdata.hy_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableEurekaServer
@SpringBootApplication
public class HyEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyEurekaApplication.class, args);
    }

}
