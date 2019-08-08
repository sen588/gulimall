package com.atguigu.gulimall.pms.service.impl;

import com.atguigu.gulimall.pms.dao.AttrAttrgroupRelationDao;
import com.atguigu.gulimall.pms.dao.AttrDao;
import com.atguigu.gulimall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.pms.entity.AttrEntity;
import com.atguigu.gulimall.pms.service.AttrAttrgroupRelationService;
import com.atguigu.gulimall.pms.vo.AttrGroupWithattrsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.pms.dao.AttrGroupDao;
import com.atguigu.gulimall.pms.entity.AttrGroupEntity;
import com.atguigu.gulimall.pms.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrDao attrDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageVo(page);
    }

    @Override
    public PageVo getPageListGroupId(QueryCondition condition, Long catId) { //1、获取封装的分页条件
        IPage<AttrGroupEntity> page = new Query<AttrGroupEntity>().getPage(condition);
        //2、获取查询条件
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>()
                .eq("catelog_id", catId);
        IPage<AttrGroupEntity> data = this.page(page, wrapper);
        //查出的所有分组  3
        List<AttrGroupEntity> records = data.getRecords();
        //将要返回出去的带分组信息以及他的属性信息的对象
        List<AttrGroupWithattrsVo> groupWithAttrs = new ArrayList<>(records.size());
        for (AttrGroupEntity record : records) {
            //1、创建一个vo准备收集所有需要的数据
            AttrGroupWithattrsVo vo = new AttrGroupWithattrsVo();
            BeanUtils.copyProperties(record,vo);

            Long groupId = record.getAttrGroupId();
            //获取当前分组的所有属性；
            List<AttrAttrgroupRelationEntity> relationEntities = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", groupId));
            if(relationEntities!=null && relationEntities.size()>0){
                List<Long> attrIds = new ArrayList<>();
                for (AttrAttrgroupRelationEntity entity : relationEntities) {
                    attrIds.add(entity.getAttrId());
                }
                //查出当前分组所有真正的属性
                List<AttrEntity> attrEntities = attrDao.selectList(new QueryWrapper<AttrEntity>().in("attr_id", attrIds));
                vo.setAttrEntities(attrEntities);
            }
            //把这个vo放在集合中
            groupWithAttrs.add(vo);
        }
        System.out.println(groupWithAttrs);

        return new PageVo(groupWithAttrs,(int)data.getTotal(),(int)data.getSize(),(int)data.getCurrent());
    }
}