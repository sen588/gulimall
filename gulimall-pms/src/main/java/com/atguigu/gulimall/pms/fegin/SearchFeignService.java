package com.atguigu.gulimall.pms.fegin;

import com.atguigu.gulimall.commons.bean.Resp;
import com.atguigu.gulimall.commons.to.es.EsSkuVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("gulimall-search")
public interface SearchFeignService {

    /**
     * 上架
     * @param vos
     * @return
     */
    @PostMapping("/search/spu/up")
    Resp<Object> spuUp(@RequestBody List<EsSkuVo> vos);

    /**
     * 下架
     * @param spuIds
     * @return
     */
    @GetMapping("/search/spu/del")
    Resp<Object> spuDel(@RequestParam(value = "spuIds") List<Long> spuIds);
}
