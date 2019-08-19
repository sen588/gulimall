package com.atguigu.gulimall.commons.to;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkuInfoVo {
    private Long skuId;//商品的id
    private String skuTitle;//商品的标题
    private String setmeal;//套餐
    private String pics;//商品图片
    private BigDecimal price;//单价
}
