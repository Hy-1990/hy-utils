package com.bigdata.hytest.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @Program: hy-utils @ClassName: Test1DBConfig @Author: huyi @Date: 2020-09-19
 * 22:30 @Description: @Version: V1.0
 */
@Configuration
@MapperScan(
    basePackages = {Test1DBConfig.PACKAGE},
    sqlSessionFactoryRef = "test1SqlSessionFactory")
public class Test1DBConfig {
  static final String PACKAGE = "com.bigdata.hytest.dao.test1";

  private static final String MAPPER_LOCAL = "classpath:mapper/test1/*.xml";

  @ConfigurationProperties("spring.datasource.druid.test1")
  @Primary
  @Bean(name = "test1DataSource")
  public DruidDataSource druidDataSource() {
    return new DruidDataSource();
  }

  @Bean(name = "test1TransactionManager")
  @Primary
  public DataSourceTransactionManager masterTransactionManager() {
    return new DataSourceTransactionManager(druidDataSource());
  }

  @Bean(name = "test1SqlSessionFactory")
  @Primary
  public SqlSessionFactory masterSqlSessionFactory(
      @Qualifier("test1DataSource") DruidDataSource dataSource) throws Exception {
    final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource);
    sessionFactoryBean.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
    return sessionFactoryBean.getObject();
  }
}
