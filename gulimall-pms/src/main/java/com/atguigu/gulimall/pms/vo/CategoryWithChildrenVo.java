package com.atguigu.gulimall.pms.vo;

import com.atguigu.gulimall.pms.entity.CategoryEntity;
import lombok.Data;

import java.util.List;
@Data
public class CategoryWithChildrenVo extends CategoryEntity {

    private List<CategoryEntity> subs;
}
