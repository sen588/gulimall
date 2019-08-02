package com.atguigu.gulimall.oms.controller;

import com.atguigu.gulimall.oms.fegin.WorldFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class WorldController {

    @Autowired
    private WorldFegin worldFegin;

    @Value("${my.content}")
    private String content = "";

    @Value("${redisUrl}")
    private String redisUrl;

    @Value("${datasurce}")
    private String datasurce;

    @GetMapping("/hello")
    public String hello()
    {
        return worldFegin.world() + content + redisUrl + datasurce;
    }
}
