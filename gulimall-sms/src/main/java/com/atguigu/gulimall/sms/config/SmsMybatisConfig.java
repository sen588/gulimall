package com.atguigu.gulimall.sms.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
public class SmsMybatisConfig {

    /*
    Mybatis分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor()
    {
        return new PaginationInterceptor();
    }

    /**
     * seata-server 技术
     *  spring 分布式事务
     * @param url
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource originDataSource(@Value("${spring.datasource.url}") String url)
    {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(url);
        return hikariDataSource;
    }

    @Bean
    @Primary
    public DataSource dataSource(DataSource dataSource)
    {
        return new DataSourceProxy(dataSource);
    }

}
