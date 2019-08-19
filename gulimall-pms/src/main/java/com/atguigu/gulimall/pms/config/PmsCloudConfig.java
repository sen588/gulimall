package com.atguigu.gulimall.pms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.atguigu.gulimall.pms.fegin")
public class PmsCloudConfig {

    @Bean("mainThreadPool")
    public ThreadPoolExecutor threadPoolExecutor(@Value("${app.main.thread.corePoolSize}") Integer corePoolSize,
                                                 @Value("${app.main.thread.maximumPoolSize}") Integer maximumPoolSize)
    {
        return new ThreadPoolExecutor(corePoolSize ,maximumPoolSize ,0L ,
                                    TimeUnit.SECONDS ,new LinkedBlockingQueue<>(maximumPoolSize/2));
    }
}
