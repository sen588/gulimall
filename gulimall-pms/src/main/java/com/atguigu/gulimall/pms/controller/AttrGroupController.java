package com.atguigu.gulimall.pms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;
import com.atguigu.gulimall.commons.bean.Resp;
import com.atguigu.gulimall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.pms.entity.AttrEntity;
import com.atguigu.gulimall.pms.service.AttrAttrgroupRelationService;
import com.atguigu.gulimall.pms.service.AttrService;
import com.atguigu.gulimall.pms.vo.AttrGroupWithAttrsVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.pms.entity.AttrGroupEntity;
import com.atguigu.gulimall.pms.service.AttrGroupService;




/**
 * 属性分组
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:40:39
 */
@Api(tags = "属性分组 管理")
@RestController
@RequestMapping("pms/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private AttrAttrgroupRelationService relationService;

    @Autowired
    private AttrService attrService;

    /**
     * 属性信息
     */
    @ApiOperation("查询某个分组以及分组下面的所有属性信息")
    @GetMapping("/info/withattrs/{attrGroupId}")
    public Resp<AttrGroupWithAttrsVo> infoWithattrs(@PathVariable("attrGroupId") Long attrGroupId)
    {
        AttrGroupWithAttrsVo attrVo = new AttrGroupWithAttrsVo();
        //查询出当前分组的信息
        AttrGroupEntity attrGroupEntity = attrGroupService.getById(attrGroupId);
        BeanUtils.copyProperties(attrGroupEntity, attrVo);

        //查询出当前分组信息和属性的所有关系
        List<AttrAttrgroupRelationEntity> relations = relationService.list(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroupId));
        attrVo.setRelations(relations);
        //将要查询的分组id存入List集合中
        ArrayList<Long> attrIds = new ArrayList<>();
        relations.forEach(relation -> {
            attrIds.add(relation.getAttrId());
        });
        //查询当前分组所有的信息
        List<AttrEntity> entities = attrService.list(new QueryWrapper<AttrEntity>().in("attr_id", attrIds));
        attrVo.setAttrEntities(entities);
        return Resp.ok(attrVo);
    }
    /**
     * 所有分组属性
     */
    @ApiOperation("查询某个分组下对应的所有属性")
    @GetMapping("/list/category/{catId}")
    public Resp<PageVo> groupList(QueryCondition queryCondition,
                                  @PathVariable("catId") Long catId)
    {
        PageVo page = attrGroupService.getPageListGroupId(queryCondition, catId);
        return Resp.ok(page);
    }

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:attrgroup:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = attrGroupService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{attrGroupId}")
    @PreAuthorize("hasAuthority('pms:attrgroup:info')")
    public Resp<AttrGroupEntity> info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        return Resp.ok(attrGroup);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('pms:attrgroup:save')")
    public Resp<Object> save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('pms:attrgroup:update')")
    public Resp<Object> update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('pms:attrgroup:delete')")
    public Resp<Object> delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return Resp.ok(null);
    }

}
