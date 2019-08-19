package com.atguigu.gulimall.pms.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.gulimall.commons.bean.Constant;
import com.atguigu.gulimall.pms.annotation.GuliCache;
import com.atguigu.gulimall.pms.vo.CategoryWithChildrenVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.pms.dao.CategoryDao;
import com.atguigu.gulimall.pms.entity.CategoryEntity;
import com.atguigu.gulimall.pms.service.CategoryService;
import org.springframework.util.StringUtils;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageVo(page);
    }

    @Override
    public List<CategoryEntity> getTreeLevelById(Integer level) {
        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        if(level != 0)
        {
            wrapper.eq("cat_level",level);
        }
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<CategoryEntity> getChildrenTreeById(Integer catId) {
        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_cid", catId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    @GuliCache(pexfix = Constant.CACHE_CATELOG)
    public List<CategoryWithChildrenVo> getLevelCategoryById(Integer catId) {
        List<CategoryWithChildrenVo> subs = baseMapper.selectCategoryWithChildrenList(catId);
        return subs;
    }
}