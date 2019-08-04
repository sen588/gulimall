package com.atguigu.gulimall.pms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.pms.dao.SpuInfoDao;
import com.atguigu.gulimall.pms.entity.SpuInfoEntity;
import com.atguigu.gulimall.pms.service.SpuInfoService;
import org.springframework.util.StringUtils;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

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

}