package com.atguigu.gulimall.pms.service;

import com.atguigu.gulimall.pms.vo.SkuItemDetailVo;

public interface ItemService {

    SkuItemDetailVo getSkuDetailById(Long skuId);
}
