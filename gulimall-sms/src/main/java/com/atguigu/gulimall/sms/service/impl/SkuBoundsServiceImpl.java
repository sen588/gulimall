package com.atguigu.gulimall.sms.service.impl;

import com.atguigu.gulimall.commons.to.SkuSaleInfoTo;
import com.atguigu.gulimall.sms.dao.SkuFullReductionDao;
import com.atguigu.gulimall.sms.dao.SkuLadderDao;
import com.atguigu.gulimall.sms.entity.SkuFullReductionEntity;
import com.atguigu.gulimall.sms.entity.SkuLadderEntity;
import org.springframework.beans.BeanUtils;
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

import com.atguigu.gulimall.sms.dao.SkuBoundsDao;
import com.atguigu.gulimall.sms.entity.SkuBoundsEntity;
import com.atguigu.gulimall.sms.service.SkuBoundsService;
import org.springframework.transaction.annotation.Transactional;


@Service("skuBoundsService")
public class SkuBoundsServiceImpl extends ServiceImpl<SkuBoundsDao, SkuBoundsEntity> implements SkuBoundsService {

    @Autowired
    private SkuBoundsDao skuBoundsDao;

    @Autowired
    private SkuLadderDao skuLadderDao;

    @Autowired
    private SkuFullReductionDao skuFullReductionDao;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<SkuBoundsEntity> page = this.page(
                new Query<SkuBoundsEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Transactional
    @Override
    public void saveSkuSaleInfoBaseById(List<SkuSaleInfoTo> tos) {
        if (tos != null && tos.size() > 0) {
            for (SkuSaleInfoTo info : tos) {
                //1、sku_bounds 积分信息的保存
                SkuBoundsEntity boundsEntity = new SkuBoundsEntity();
                //优惠生效情况[1111（四个状态位，从右到左）;
                // 0 - 无优惠，成长积分是否赠送;
                // 1 - 无优惠，购物积分是否赠送;
                // 2 - 有优惠，成长积分是否赠送;
                // 3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]
                Integer[] work = info.getWork();
                Integer i = work[3] * 1 + work[2] * 2 + work[1] * 4 + work[0] * 8;
                boundsEntity.setWork(i);
                boundsEntity.setBuyBounds(info.getBuyBounds());
                boundsEntity.setGrowBounds(info.getGrowBounds());
                boundsEntity.setSkuId(info.getSkuId());
                skuBoundsDao.insert(boundsEntity);

                //2、sku_ladder  阶梯价格的保存
                SkuLadderEntity ladderEntity = new SkuLadderEntity();
                ladderEntity.setFullCount(info.getFullCount());
                ladderEntity.setDiscount(info.getDiscount());
                ladderEntity.setAddOther(info.getLadderAddOther());
                ladderEntity.setSkuId(info.getSkuId());
                skuLadderDao.insert(ladderEntity);


                //3、sku_full_reduction 满减信息保存
                SkuFullReductionEntity fullReductionEntity = new SkuFullReductionEntity();
                BeanUtils.copyProperties(info, fullReductionEntity);
                fullReductionEntity.setAddOther(info.getFullAddOther());
                skuFullReductionDao.insert(fullReductionEntity);

            }
        }
    }
}