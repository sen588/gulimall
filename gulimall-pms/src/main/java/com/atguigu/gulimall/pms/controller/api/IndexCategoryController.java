package com.atguigu.gulimall.pms.controller.api;

import com.atguigu.gulimall.commons.bean.Resp;
import com.atguigu.gulimall.pms.entity.CategoryEntity;
import com.atguigu.gulimall.pms.service.CategoryService;
import com.atguigu.gulimall.pms.vo.CategoryWithChildrenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "前端三级显示分类")
@RestController
@RequestMapping("api/index")
public class IndexCategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取所有的一级分类")
    @GetMapping("/cates")
    public Resp<List<CategoryEntity>> levelOneCatelogs() {
        List<CategoryEntity> data = categoryService.getTreeLevelById(1);
        return Resp.ok(data);
    }

    @ApiOperation("获取所有的二和三级分类")
    @GetMapping("/cates/{catId}")
    public Resp<List<CategoryWithChildrenVo>> levelToweCatelogs(@PathVariable("catId") Integer catId) {
        List<CategoryWithChildrenVo> data = categoryService.getLevelCategoryById(catId);
        return Resp.ok(data);
    }
}
