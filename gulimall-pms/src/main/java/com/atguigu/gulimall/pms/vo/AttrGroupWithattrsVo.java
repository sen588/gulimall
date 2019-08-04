package com.atguigu.gulimall.pms.vo;

import com.atguigu.gulimall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.pms.entity.AttrEntity;
import com.atguigu.gulimall.pms.entity.AttrGroupEntity;
import lombok.Data;

import java.util.List;

@Data
public class AttrGroupWithattrsVo extends AttrGroupEntity {

    private List<AttrAttrgroupRelationEntity> relations;

    private List<AttrEntity> attrEntities;
}
