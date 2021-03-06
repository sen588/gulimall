package com.atguigu.gulimall.pms.service.impl;

import com.atguigu.gulimall.commons.to.SkuInfoVo;
import com.atguigu.gulimall.pms.dao.SkuSaleAttrValueDao;
import com.atguigu.gulimall.pms.entity.SkuSaleAttrValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.pms.dao.SkuInfoDao;
import com.atguigu.gulimall.pms.entity.SkuInfoEntity;
import com.atguigu.gulimall.pms.service.SkuInfoService;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Autowired
    private SkuInfoDao skuInfoDao;

    @Autowired
    private SkuSaleAttrValueDao skuSaleAttrValueDao;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Override
    public SkuInfoVo getSkuVo(Long skuId) {
        SkuInfoEntity entity = skuInfoDao.selectById(skuId);
        SkuInfoVo vo = new SkuInfoVo();
        vo.setSkuId(skuId);
        vo.setPrice(entity.getPrice());
        vo.setSkuTitle(entity.getSkuTitle());
        vo.setPics(entity.getSkuDefaultImg());
        List<SkuSaleAttrValueEntity> values = skuSaleAttrValueDao.selectList(new QueryWrapper<SkuSaleAttrValueEntity>()
                                                                 .eq("sku_id", skuId));
        String meal = "";
        for (SkuSaleAttrValueEntity value : values) {
            meal += "_" + value.getAttrValue();
        }
        //套餐
        vo.setSetmeal(meal);
        return vo;
    }

}