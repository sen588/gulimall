package com.atguigu.gulimall.pms.service.impl;

import com.atguigu.gulimall.pms.entity.SkuImagesEntity;
import com.atguigu.gulimall.pms.entity.SkuInfoEntity;
import com.atguigu.gulimall.pms.service.*;
import com.atguigu.gulimall.pms.vo.SkuItemDetailVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;


@Service("ItemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    @Qualifier("mainThreadPool")
    private ThreadPoolExecutor mainThreadPool;

    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Override
    public SkuItemDetailVo getSkuDetailById(Long skuId) {
        SkuItemDetailVo vos = new SkuItemDetailVo();
        //当前sku的基本属性
        CompletableFuture<SkuInfoEntity> skuInfo = CompletableFuture.supplyAsync(() -> {
            SkuInfoEntity sku = skuInfoService.getById(skuId);
            return sku;
        }, mainThreadPool);
        CompletableFuture<Void> skuInfoSen = skuInfo.thenAcceptAsync((t) -> {
            BeanUtils.copyProperties(t, vos);
        }, mainThreadPool);

        //sku所有图片
        CompletableFuture<List<SkuImagesEntity>> images = CompletableFuture.supplyAsync(() -> {
            List<SkuImagesEntity> img = skuImagesService.list(new QueryWrapper<SkuImagesEntity>()
                    .eq("sku_id", skuId));
            return img;
        }, mainThreadPool);
        CompletableFuture<Void> imagesSen = images.thenAcceptAsync((t) -> {
            BeanUtils.copyProperties(t, vos);
        }, mainThreadPool);

        //sku所有促销信息

        //sku所有销售信息组合


        //spu所有的基本属性
        //详情介绍
        CompletableFuture<Void> spuInfoSen = skuInfo.thenAcceptAsync((skuInfoEntity) -> {
            Long id = skuInfoEntity.getSkuId();
            vos.setDesc(spuInfoDescService.getById(id));
        }, mainThreadPool);
        CompletableFuture<Void> future = CompletableFuture.allOf(
                skuInfo, skuInfoSen,
                images, imagesSen,
                spuInfoSen);
        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //多线程；提升吞吐量；
        return vos;
    }
}
