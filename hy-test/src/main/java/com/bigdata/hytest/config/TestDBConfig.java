package com.bigdata.hytest.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.activation.DataSource;

/**
 * @Program: hy-utils @ClassName: TestDBConfig @Author: huyi @Date: 2020-09-19
 * 21:43 @Description: @Version: V1.0
 */
@Configuration
@MapperScan(
    basePackages = {TestDBConfig.PACKAGE},
    sqlSessionFactoryRef = "testSqlSessionFactory")
public class TestDBConfig {
  static final String PACKAGE = "com.bigdata.hytest.dao.test";

  private static final String MAPPER_LOCAL = "classpath:mapper/test/*.xml";

  @ConfigurationProperties("spring.datasource.druid.test")
  @Bean(name = "testDataSource")
  public DruidDataSource druidDataSource() {
    return new DruidDataSource();
  }

  @Bean(name = "testTransactionManager")
  public DataSourceTransactionManager masterTransactionManager() {
    return new DataSourceTransactionManager(druidDataSource());
  }

  @Bean(name = "testSqlSessionFactory")
  public SqlSessionFactory masterSqlSessionFactory(
      @Qualifier("testDataSource") DruidDataSource dataSource) throws Exception {
    final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource);
    sessionFactoryBean.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
    return sessionFactoryBean.getObject();
  }
}
