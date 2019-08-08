package com.atguigu.gulimall.pms.service.impl;

import com.atguigu.gulimall.commons.to.SkuSaleInfoTo;
import com.atguigu.gulimall.commons.utils.AppUtils;
import com.atguigu.gulimall.pms.dao.*;
import com.atguigu.gulimall.pms.entity.*;
import com.atguigu.gulimall.pms.fegin.PmsSkuBoundsFeginService;
import com.atguigu.gulimall.pms.vo.BaseAttrsVo;
import com.atguigu.gulimall.pms.vo.SaleAttrsVo;
import com.atguigu.gulimall.pms.vo.SkusVo;
import com.atguigu.gulimall.pms.vo.SpuAllSaveVo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.pms.service.SpuInfoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuInfoDescDao spuInfoDescDao;

    @Autowired
    private SkuInfoDao skuInfoDao;

    @Autowired
    private SkuImagesDao skuImagesDao;

    @Autowired
    private ProductAttrValueDao productAttrValueDao;

    @Autowired
    private AttrDao attrDao;

    @Autowired
    private SkuSaleAttrValueDao skuSaleAttrValueDao;

    /**
     * 远程调用
     */
    @Autowired
    private PmsSkuBoundsFeginService pmsSkuBoundsFeginService;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageVo(page);
    }

    @Override
    public PageVo queryPageConditionCatId(QueryCondition condition, Long catId) {
        QueryWrapper<SpuInfoEntity> queryWrapper = new QueryWrapper<>();
        if(catId != 0)
        {
            queryWrapper.eq("catalog_id", catId);
        }
        String key = condition.getKey();
        if(!StringUtils.isEmpty(key))
        {
            queryWrapper.and(obj ->{
                //函数师编程
                obj.like("spu_name", key);
                obj.or().like("id", key);
                return obj;
            });
        }
        //封面翻页条件
        IPage<SpuInfoEntity> page = new Query<SpuInfoEntity>().getPage(condition);
        //去数据库查询
        IPage<SpuInfoEntity> data = this.page(page, queryWrapper);
        return new PageVo(data);
    }

    @GlobalTransactional
    @Override
    public void saveSpuAll(SpuAllSaveVo allSaveVo) {
        SpuInfoService infoService = (SpuInfoService)AopContext.currentProxy();
        //5.1.46
        //保存spu基本属性
        Long spuId = infoService.saveSpuBigById(allSaveVo);
        //保存spu图片信息
        infoService.saveSpuInfoIamgeById(spuId, allSaveVo.getSpuImages());
        //保存spu属性信息
        infoService.saveBaseAttrsById(spuId, allSaveVo.getBaseAttrs());
        //保存sku信息
        infoService.saveSkusBaseById(spuId, allSaveVo.getSkus());
    }

    @Transactional
    @Override
    public Long saveSpuBigById(SpuAllSaveVo allSaveVo) {
        //保存spu基本信息
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(allSaveVo, spuInfoEntity);
        //注册时间
        spuInfoEntity.setCreateTime(new Date());
        //修改时间
        spuInfoEntity.setUodateTime(new Date());
        //保存
        baseMapper.insert(spuInfoEntity);
        //返回spu id值
        return spuInfoEntity.getId();
    }

    @Transactional
    @Override
    public void saveSpuInfoIamgeById(Long spuId, String[] spuImages) {
        SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
        descEntity.setSpuId(spuId);
        descEntity.setDecript(AppUtils.arrayToStringWithSeperator(spuImages, ","));
        spuInfoDescDao.insert(descEntity);
    }

    @Transactional
    @Override
    public void saveBaseAttrsById(Long spuId, List<BaseAttrsVo> baseAttrs) {
        ArrayList<ProductAttrValueEntity> attrEntity = new ArrayList<>();

        for (BaseAttrsVo baseAttr : baseAttrs) {
            ProductAttrValueEntity attrValueEntity = new ProductAttrValueEntity();
            attrValueEntity.setSpuId(spuId);
            attrValueEntity.setAttrId(baseAttr.getAttrId());
            attrValueEntity.setAttrName(baseAttr.getAttrName());
            String[] valueSelected = baseAttr.getValueSelected();

            attrValueEntity.setAttrValue(AppUtils.arrayToStringWithSeperator(valueSelected, ","));
            attrValueEntity.setAttrSort(0);
            attrValueEntity.setQuickShow(1);
            attrEntity.add(attrValueEntity);
        }
        productAttrValueDao.insertBatch(attrEntity);
    }

    /**
     保存sku信息
     */
    @Transactional
    @Override
    public void saveSkusBaseById(Long spuId, List<SkusVo> skus) {
        SpuInfoEntity spuEntity = baseMapper.selectById(spuId);

        List<SkuSaleInfoTo> tos = new ArrayList<>();
        for (SkusVo sku : skus) {
            SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
            String[] images = sku.getImages();
            skuInfoEntity.setSpuId(spuEntity.getId());
            skuInfoEntity.setSkuCode(UUID.randomUUID().toString().substring(0, 6).toUpperCase());
            skuInfoEntity.setSkuName(sku.getSkuName());
            skuInfoEntity.setSkuDesc(sku.getSkuDesc());
            skuInfoEntity.setCatalogId(spuEntity.getCatalogId());
            skuInfoEntity.setBrandId(spuEntity.getBrandId());
            if (images != null && images.length > 0) {
                skuInfoEntity.setSkuDefaultImg(sku.getImages()[0]);
            }
            skuInfoEntity.setSkuTitle(sku.getSkuTitle());
            skuInfoEntity.setSkuSubtitle(sku.getSkuSubtitle());
            skuInfoEntity.setPrice(sku.getPrice());
            skuInfoEntity.setWeight(sku.getWeight());
            //保存xku基本信息
            skuInfoDao.insert(skuInfoEntity);

            //获取sku id值
            Long skuId = spuEntity.getId();
            //保存sku的所有图片
            SkuImagesEntity imagesEntity = new SkuImagesEntity();
            for (int i = 0; i < images.length; i++) {
                imagesEntity.setSkuId(skuId);
                imagesEntity.setDefaultImg(i == 0 ? 1 : 0);
                imagesEntity.setImgUrl(images[i]);
                imagesEntity.setImgSort(0);
                skuImagesDao.insert(imagesEntity);
            }
            //保存sku销售属性组合
            List<SaleAttrsVo> saleAttrs = sku.getSaleAttrs();
            for (SaleAttrsVo saleAttr : saleAttrs) {
                SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                skuSaleAttrValueEntity.setAttrId(saleAttr.getAttrId());
                AttrEntity attrEntity = attrDao.selectById(saleAttr.getAttrId());
                skuSaleAttrValueEntity.setAttrName(attrEntity.getAttrName());
                skuSaleAttrValueEntity.setAttrSort(0);
                skuSaleAttrValueEntity.setAttrValue(saleAttr.getAttrValue());
                skuSaleAttrValueEntity.setSkuId(skuId);
                //保存完成
                skuSaleAttrValueDao.insert(skuSaleAttrValueEntity);
            }
            //保存优惠信息，由sms完成
            SkuSaleInfoTo info = new SkuSaleInfoTo();
            BeanUtils.copyProperties(sku, info);
            info.setSkuId(skuId);
            tos.add(info);
        }
        //调用远程方法
        pmsSkuBoundsFeginService.saveSkuSaleInfo(tos);
    }
}