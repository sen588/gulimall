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

import com.atguigu.gulimall.wms.entity.SysRoleMenuEntity;
import com.atguigu.gulimall.wms.service.SysRoleMenuService;




/**
 * 角色与菜单对应关系
 *
 * @author ÀîÉ­
 * @email 2050531502@qq.com
 * @date 2019-08-01 19:28:52
 */
@Api(tags = "角色与菜单对应关系 管理")
@RestController
@RequestMapping("wms/sysrolemenu")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:sysrolemenu:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = sysRoleMenuService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('wms:sysrolemenu:info')")
    public Resp<SysRoleMenuEntity> info(@PathVariable("id") Long id){
		SysRoleMenuEntity sysRoleMenu = sysRoleMenuService.getById(id);

        return Resp.ok(sysRoleMenu);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:sysrolemenu:save')")
    public Resp<Object> save(@RequestBody SysRoleMenuEntity sysRoleMenu){
		sysRoleMenuService.save(sysRoleMenu);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:sysrolemenu:update')")
    public Resp<Object> update(@RequestBody SysRoleMenuEntity sysRoleMenu){
		sysRoleMenuService.updateById(sysRoleMenu);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:sysrolemenu:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		sysRoleMenuService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
