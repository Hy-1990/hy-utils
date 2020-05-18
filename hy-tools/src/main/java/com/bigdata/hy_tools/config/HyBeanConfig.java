package com.bigdata.hy_tools.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huyi
 * @date 2020/4/29 4:40 PM
 */
@Configuration
public class HyBeanConfig {

    @Bean(name = "TT")
    public String hyParam() {
        return "Trump is SB!";
    }
}
