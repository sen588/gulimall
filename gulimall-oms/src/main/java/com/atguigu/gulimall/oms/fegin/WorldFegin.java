package com.atguigu.gulimall.oms.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(name = "gulimall-pms")
public interface WorldFegin {

    @GetMapping("/world")
    String world();
}
