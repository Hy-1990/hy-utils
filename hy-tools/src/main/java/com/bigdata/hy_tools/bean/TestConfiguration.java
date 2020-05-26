package com.bigdata.hy_tools.bean;

import com.bigdata.hy_tools.utils.TestUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huyi
 * @date 2020/5/25 18:04
 */
@Configuration
public class TestConfiguration {

    @Bean(name = "testUtil")
    public TestUtil testUtil() {
        return new TestUtil();
    }
}
