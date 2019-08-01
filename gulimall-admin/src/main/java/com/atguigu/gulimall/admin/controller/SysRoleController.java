package com.atguigu.gulimall.wms.controller;

import java.util.Arrays;
import java.util.Map;


import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;
import com.atguigu.gulimall.commons.bean.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.wms.entity.SysRoleEntity;
import com.atguigu.gulimall.wms.service.SysRoleService;




/**
 * 角色
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = "角色 管理")
@RestController
@RequestMapping("wms/sysrole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:sysrole:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = sysRoleService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{roleId}")
    @PreAuthorize("hasAuthority('wms:sysrole:info')")
    public Resp<SysRoleEntity> info(@PathVariable("roleId") Long roleId){
		SysRoleEntity sysRole = sysRoleService.getById(roleId);

        return Resp.ok(sysRole);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:sysrole:save')")
    public Resp<Object> save(@RequestBody SysRoleEntity sysRole){
		sysRoleService.save(sysRole);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:sysrole:update')")
    public Resp<Object> update(@RequestBody SysRoleEntity sysRole){
		sysRoleService.updateById(sysRole);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:sysrole:delete')")
    public Resp<Object> delete(@RequestBody Long[] roleIds){
		sysRoleService.removeByIds(Arrays.asList(roleIds));

        return Resp.ok(null);
    }

}
