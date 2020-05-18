package com.bigdata.hy_tools;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaClient
@SpringCloudApplication
//@MapperScan("com.bigdata.hy_tools.dao")
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class HyToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyToolsApplication.class, args);
    }

}
