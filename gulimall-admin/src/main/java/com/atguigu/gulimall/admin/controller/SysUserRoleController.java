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

import com.atguigu.gulimall.wms.entity.SysUserRoleEntity;
import com.atguigu.gulimall.wms.service.SysUserRoleService;




/**
 * 用户与角色对应关系
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = "用户与角色对应关系 管理")
@RestController
@RequestMapping("wms/sysuserrole")
public class SysUserRoleController {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:sysuserrole:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = sysUserRoleService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('wms:sysuserrole:info')")
    public Resp<SysUserRoleEntity> info(@PathVariable("id") Long id){
		SysUserRoleEntity sysUserRole = sysUserRoleService.getById(id);

        return Resp.ok(sysUserRole);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:sysuserrole:save')")
    public Resp<Object> save(@RequestBody SysUserRoleEntity sysUserRole){
		sysUserRoleService.save(sysUserRole);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:sysuserrole:update')")
    public Resp<Object> update(@RequestBody SysUserRoleEntity sysUserRole){
		sysUserRoleService.updateById(sysUserRole);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:sysuserrole:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		sysUserRoleService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
