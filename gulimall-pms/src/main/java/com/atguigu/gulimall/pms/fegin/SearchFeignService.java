package com.atguigu.gulimall.pms.fegin;

import com.atguigu.gulimall.commons.bean.Resp;
import com.atguigu.gulimall.commons.to.es.EsSkuVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("gulimall-search")
public interface SearchFeignService {


    @PostMapping("/es/search/spu/up")
    Resp<Object> spuUp(@RequestBody List<EsSkuVo> vos);
}
