package com.atguigu.gulimall.oms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.gulimall.oms.dao")
public class GulimallomsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallomsApplication.class, args);
    }

}