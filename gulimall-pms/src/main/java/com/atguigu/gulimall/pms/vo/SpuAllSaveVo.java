package com.atguigu.gulimall.pms.vo;

import com.atguigu.gulimall.pms.entity.SpuInfoEntity;
import lombok.Data;

import java.util.List;


@Data
public class SpuAllSaveVo extends SpuInfoEntity {

    private List<BaseAttrsVo> baseAttrs;

    private List<SkusVo> skus;

    /**
    spu详情图
     */
    private String[] spuImages;
}
