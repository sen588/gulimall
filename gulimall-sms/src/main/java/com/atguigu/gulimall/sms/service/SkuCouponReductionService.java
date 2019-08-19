package com.atguigu.gulimall.sms.service;

import com.atguigu.gulimall.commons.to.SkuReductionTo;
import com.atguigu.gulimall.sms.to.SkuCouponTo;

import java.util.List;

public interface SkuCouponReductionService {
    List<SkuCouponTo> getCoupons(Long skuId);

    List<SkuReductionTo> getRedutions(Long skuId);
}
