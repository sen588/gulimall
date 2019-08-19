package com.atguigu.gulimall.cart.config;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@Configuration
public class CartCloudConfig {

    @Bean("mainExecutor")
    @Primary  //默认就是他
    public ThreadPoolExecutor mainThreadPoolExecutor(){
        //cpu核；
        //无界队列；
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 100, 0L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>( 50));

        return executor;
    }

    @Bean("otherExecutor")
    public ThreadPoolExecutor noMainThreadPoolExecutor(){
        //cpu核；
        //无界队列；
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 100, 0L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(50));

        return executor;
    }


}
