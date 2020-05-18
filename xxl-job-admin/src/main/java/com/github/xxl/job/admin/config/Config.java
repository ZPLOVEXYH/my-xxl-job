package com.github.xxl.job.admin.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by ZhangPeng on 2018/4/18.
 */
@Configuration
@ComponentScan(
        {
                "com.xxl.job.admin",
                "com.github.xxl.job.admin.config"
        })
@PropertySource(value = "classpath:xxl-job-admin.properties")
public class Config {


    /**
     * applicationcontext-xxl-job-admin.xml
     **/
    @Bean
    public DataSource dataSource(XxlDBConfig xxlDBConfig) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(xxlDBConfig.getDriverClass());
        dataSource.setJdbcUrl(xxlDBConfig.getUrl());
        dataSource.setUser(xxlDBConfig.getUser());
        dataSource.setPassword(xxlDBConfig.getPassword());
        dataSource.setInitialPoolSize(xxlDBConfig.getInitialPoolSize());
        dataSource.setMaxPoolSize(xxlDBConfig.getMaxPoolSize());
        dataSource.setMinPoolSize(xxlDBConfig.getMinPoolSize());
        dataSource.setMaxIdleTime(xxlDBConfig.getMaxIdleTime());
        dataSource.setAcquireRetryDelay(xxlDBConfig.getAcquireRetryDelay());
        dataSource.setAcquireRetryAttempts(xxlDBConfig.getAcquireRetryAttempts());
        dataSource.setPreferredTestQuery(xxlDBConfig.getPreferredTestQuery());
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis-mapper/*.xml");

        sessionFactoryBean.setMapperLocations(resources);
        return sessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setBasePackage("com.xxl.job.admin.dao");
        return configurer;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
