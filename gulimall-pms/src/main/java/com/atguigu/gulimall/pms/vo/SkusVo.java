package com.atguigu.gulimall.pms.vo;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkusVo
{
    private String skuName;

    private String skuDesc;

    private String skuTitle;

    private String skuSubtitle;

    private BigDecimal weight;

    private BigDecimal price;

    private String[] images;

    private List<SaleAttrsVo> saleAttrs;

    private BigDecimal buyBounds;

    private BigDecimal growBounds;

    private Integer[] work;

    private Integer fullCount;

    private BigDecimal discount;

    private Integer ladderAddOther;

    private BigDecimal fullPrice;

    private BigDecimal reducePrice;

    private String fullAddOther;
}

