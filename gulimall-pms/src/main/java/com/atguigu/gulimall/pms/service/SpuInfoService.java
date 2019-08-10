package com.atguigu.gulimall.pms.service;

import com.atguigu.gulimall.commons.to.SkuSaleInfoTo;
import com.atguigu.gulimall.pms.vo.BaseAttrsVo;
import com.atguigu.gulimall.pms.vo.SkusVo;
import com.atguigu.gulimall.pms.vo.SpuAllSaveVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.pms.entity.SpuInfoEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import java.util.List;


/**
 * spu信息
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:40:38
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageVo queryPage(QueryCondition params);

    /**
    分类id检索商品
     */
    PageVo queryPageConditionCatId(QueryCondition condition, Long catId);

    void saveSpuAll(SpuAllSaveVo allSaveVo);

    Long saveSpuBigById(SpuAllSaveVo allSaveVo);

    void saveSpuInfoIamgeById(Long spuId, String[] spuImages);

    void saveBaseAttrsById(Long spuId, List<BaseAttrsVo> baseAttrs);

    void saveSkusBaseById(Long spuId, List<SkusVo> skus);

    void updateSpuStatus(Long spuId, Integer status);

    void Grounding(Long spuId);

    void Undercarriage(Long spuId);
}

